package nl.ahmed.common.kotlin.operation

import javax.inject.Inject
import nl.ahmed.common.kotlin.operation.models.OperationResult
import nl.ahmed.common.kotlin.utils.Logger

class OperationExecutor @Inject constructor(
    private val logger: Logger
) {
    suspend fun <D> execute(operation: suspend () -> OperationResult<D>): OperationResult<D> {
        return try {
            operation()
        } catch (e: Exception) {
            OperationResult.Failure(e)
        }
    }
}
