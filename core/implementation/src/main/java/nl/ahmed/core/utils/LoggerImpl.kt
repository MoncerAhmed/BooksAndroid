package nl.ahmed.core.utils

import nl.ahmed.common.kotlin.utils.Logger
import timber.log.Timber

internal class LoggerImpl : Logger {

    init {
        Timber.plant(Timber.DebugTree())
    }

    override fun logVerbose(throwable: Throwable, message: String) {
        Timber.v(throwable, message)
    }

    override fun logVerbose(message: String) {
        Timber.v(message)
    }

    override fun logInfo(throwable: Throwable, message: String) {
        Timber.i(throwable, message)
    }

    override fun logInfo(message: String) {
        Timber.i(message)
    }

    override fun logDebug(throwable: Throwable, message: String) {
        Timber.d(throwable, message)
    }

    override fun logDebug(message: String) {
        Timber.d(message)
    }

    override fun logWarning(throwable: Throwable, message: String) {
        Timber.w(throwable, message)
    }

    override fun logWarning(message: String) {
        Timber.w(message)
    }

    override fun logError(throwable: Throwable, message: String) {
        Timber.e(throwable, message)
    }

    override fun logError(message: String) {
        Timber.e(message)
    }
}
