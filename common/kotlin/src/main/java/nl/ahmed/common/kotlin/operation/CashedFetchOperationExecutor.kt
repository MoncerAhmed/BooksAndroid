package nl.ahmed.common.kotlin.operation

import javax.inject.Inject
import nl.ahmed.common.kotlin.operation.models.OperationException
import nl.ahmed.common.kotlin.operation.models.OperationResult
import nl.ahmed.common.kotlin.utils.Logger
import nl.ahmed.templates.kotlin.data.Dao
import nl.ahmed.templates.kotlin.data.Mapper
import nl.ahmed.templates.kotlin.data.Model

class CashedFetchOperationExecutor<DTO : Model.Dto, E : Model.Entity, D: Model.Data> @Inject constructor(
    private val updateDao: Dao.Update<E>,
    private val queryDao: Dao.Query<E>,
    private val dtoToEntityMapper: Mapper<DTO, E>,
    private val entityToDataMapper: Mapper<E, D>,
    private val logger: Logger
) {

    suspend fun execute(
        id: Model.Entity.Id,
        apiOperation: suspend () -> DTO
    ): OperationResult<D> {
        try {
            val apiData = apiOperation()
            var storageData = dtoToEntityMapper(apiData)
            try {
                // Insert/update new/old data
                updateDao.update(storageData)

                // Always query locale storage (if possible) because it's the source of truth
                storageData = queryDao.get(id)
            } catch (storageThrowable: Throwable) {
                // If inserting of querying the data in/from local storage didn't succeed
                // no need to fail the entire operation since we still have the API data
                logger.logError(
                    throwable = storageThrowable,
                    message = "Error update local storage"
                )
            }
            return OperationResult.Success(data = entityToDataMapper(storageData))
        } catch (apiThrowable: Throwable) {
            logger.logError(
                throwable = apiThrowable,
                message = "Error fetching data from API"
            )
            // Try to fetch local storage
            try {
                val storageData = queryDao.get(id)
                return OperationResult.Success(data = entityToDataMapper(storageData))
            } catch (storageThrowable: Throwable) {
                val operationThrowable = OperationException(
                    causes = listOf(
                        apiThrowable,
                        storageThrowable
                    )
                )
                logger.logError(
                    throwable = operationThrowable,
                    message = "Failed to fetch data from API and local storage"
                )
                // In case of storage query failure we can't get the data from any source
                // then log the error and return a failure
                return OperationResult.Failure(throwable = operationThrowable)
            }
        }
    }
}
