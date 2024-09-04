package nl.ahmed.common.kotlin.di

import nl.ahmed.common.kotlin.utils.Logger

interface LoggerProvidingComponent {
    fun logger(): Logger
}
