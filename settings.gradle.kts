pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Books"
include(":app")
include(":common:android")
include(":core:api")
include(":core:implementation")
include(":data:network:api")
include(":data:network:implementation")
include(":data:storage:api")
include(":data:storage:implementation")
include(":common:kotlin")
include(":data:dal:api")
include(":data:dal:implementation")
include(":features:home")
include(":designsystem")
include(":features:favorites")
include(":navigation")
include(":templates:android")
include(":templates:kotlin")
