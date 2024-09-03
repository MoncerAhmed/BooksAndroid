package nl.ahmed.common.kotlin.operation.models

sealed interface OperationResult<D> {
    data class Success<D>(
        val data: D
    ) : OperationResult<D>

    data class Failure<D>(
        val throwable: Throwable
    ) : OperationResult<D>
}
