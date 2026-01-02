enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
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

rootProject.name = "GloriaArtis"
include(":app")
include(":core:ui")
include(":core:domain")
include(":core:utils")
include(":data:impl")
include(":feature:login:api")
include(":feature:login:impl")
include(":feature:signup:api")
include(":feature:signup:impl")
include(":feature:profile:api")
include(":feature:profile:impl")
include(":feature:artlist:api")
include(":feature:artlist:impl")
include(":feature:artdetails:api")
include(":feature:artdetails:impl")
include(":navigation:api")
include(":navigation:impl")
