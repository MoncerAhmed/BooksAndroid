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
include(":designsystem:implementation")
include(":navigation")
include(":templates:android")
include(":templates:kotlin")
include(":features:home:shared")
include(":features:home:data")
include(":features:home:domain:api")
include(":features:home:domain:implementation")
include(":features:home:presentation:implementation")
include(":features:home:presentation:api")
include(":features:home:ui")
include(":features:favorites:shared")
include(":features:favorites:data")
include(":features:favorites:domain:api")
include(":features:favorites:domain:implementation")
include(":features:favorites:presentation:implementation")
include(":features:favorites:presentation:api")
include(":features:favorites:ui")
include(":features:details:shared")
include(":features:details:data")
include(":features:details:domain:api")
include(":features:details:domain:implementation")
include(":features:details:presentation:implementation")
include(":features:details:presentation:api")
include(":features:details:ui")
include(":designsystem:api")
