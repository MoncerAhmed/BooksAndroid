package nl.ahmed.common.kotlin.operation

import javax.inject.Inject
import nl.ahmed.common.kotlin.Mapper
import nl.ahmed.common.kotlin.templates.Dao
import nl.ahmed.common.kotlin.templates.Model

class CashedFetchOperationExecutor<DTO : Model.Dto, E : Model.Entity, D : Model.Domain> @Inject constructor(
    private val insertDao: Dao.Insert<E>,
    private val queryDao: Dao.Query<E>,
    private val deleteDao: Dao.Delete<E>,
    private val dtoToEntityMapper: Mapper<List<DTO>, List<E>>,
    private val entityToDomainMapper: Mapper<List<E>, List<D>>,
) {

    suspend fun execute(
        apiOperation: suspend () -> List<DTO>
    ): List<D> {
        try {
            val apiData = apiOperation()
            var storageData = dtoToEntityMapper(apiData)
            try {
                // Delete all stored data
                deleteDao.deleteAll()

                // Insert new data
                insertDao.insert(storageData)

                // Always query locale storage (if possible) because it's the source of truth
                storageData = queryDao.getAll()
            } catch (storageThrowable: Throwable) {
                // If inserting of querying the data in/from local storage didn't succeed
                // no need to fail the entire operation since we still have the API data
                // TODO: Log error
            }
            return entityToDomainMapper(storageData)
        } catch (apiThrowable: Throwable) {
            // TODO: Log error
            // Try to fetch local storage
            try {
                val storageData = queryDao.getAll()
                return entityToDomainMapper(storageData)
            } catch (storageThrowable: Throwable) {
                // In case of storage query failure we can't get the data from any source
                // then log the error and throw the exception
                // TODO: Log error
                throw OperationException(
                    causes = listOf(
                        apiThrowable,
                        storageThrowable
                    )
                )
            }
        }
    }
}
