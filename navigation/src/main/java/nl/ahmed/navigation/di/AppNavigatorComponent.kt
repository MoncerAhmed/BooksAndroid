package nl.ahmed.navigation.di

import nl.ahmed.navigation.AppNavigator

interface AppNavigatorComponent {
    fun appNavigator(): AppNavigator
}
