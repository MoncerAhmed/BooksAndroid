package nl.ahmed.common.kotlin.operation

import javax.inject.Inject
import nl.ahmed.templates.kotlin.Mapper
import nl.ahmed.common.kotlin.operation.models.OperationException
import nl.ahmed.common.kotlin.operation.models.OperationResult
import nl.ahmed.templates.kotlin.Dao
import nl.ahmed.templates.kotlin.Model
import nl.ahmed.common.kotlin.utils.Logger

class CashedFetchOperationExecutor<DTO : Model.Dto, E : Model.Entity, D: Model.Data> @Inject constructor(
    private val updateDao: Dao.Update<E>,
    private val queryDao: Dao.Query<E>,
    private val dtoToEntityMapper: Mapper<List<DTO>, List<E>>,
    private val entityToDataMapper: Mapper<List<E>, List<D>>,
    private val logger: Logger
) {

    suspend fun execute(
        apiOperation: suspend () -> List<DTO>
    ): OperationResult<List<D>> {
        try {
            val apiData = apiOperation()
            var storageData = dtoToEntityMapper(apiData)
            try {
                // Insert/update new/old data
                updateDao.updateAll(storageData)

                // Always query locale storage (if possible) because it's the source of truth
                storageData = queryDao.getAll()
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
                val storageData = queryDao.getAll()
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
