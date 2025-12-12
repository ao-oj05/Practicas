pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
    
    // Configuración de versiones de plugins
    plugins {
        id("com.google.devtools.ksp") version "1.9.22-1.0.16"
        id("com.google.dagger.hilt.android") version "2.48.1"
    }
}

// Configurar el repositorio de dependencias
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "ExamU3"
include(":app")
 