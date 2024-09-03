package nl.ahmed.common.kotlin.operation.models

data class OperationException(
    val causes: List<Throwable>
) : Throwable(message = causes.joinToString("\n") { it.message ?: it.toString() })
