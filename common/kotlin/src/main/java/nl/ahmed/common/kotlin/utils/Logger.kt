package nl.ahmed.common.kotlin.utils

interface Logger {
    fun logVerbose(throwable: Throwable, message: String)
    fun logVerbose(message: String)

    fun logInfo(throwable: Throwable, message: String)
    fun logInfo(message: String)

    fun logDebug(throwable: Throwable, message: String)
    fun logDebug(message: String)

    fun logWarning(throwable: Throwable, message: String)
    fun logWarning(message: String)

    fun logError(throwable: Throwable, message: String)
    fun logError(message: String)
}
