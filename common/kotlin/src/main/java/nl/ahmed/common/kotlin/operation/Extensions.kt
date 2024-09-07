package nl.ahmed.common.kotlin.operation

import nl.ahmed.common.kotlin.operation.models.OperationResult

inline fun <DI, DO> OperationResult<DI>.mapIfSuccessful(
    reducer: (DI) -> DO
): OperationResult<DO> {
    return when(this) {
        is OperationResult.Success -> OperationResult.Success(reducer(data))
        is OperationResult.Failure -> OperationResult.Failure(throwable)
    }
}

inline fun <DI, DO> OperationResult<DI>.mapData(
    successReducer: (DI) -> DO,
    failureReducer: (Throwable) -> DO
): DO {
    return when(this) {
        is OperationResult.Success -> successReducer(data)
        is OperationResult.Failure -> failureReducer(throwable)
    }
}

inline fun <D> OperationResult<D>.doIfSuccessful(
    block: (D) -> Unit
): OperationResult<D> {
    if(this is OperationResult.Success) {
        block(this.data)
    }
    return this
}
