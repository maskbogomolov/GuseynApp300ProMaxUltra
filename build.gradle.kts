plugins {
    id("com.android.application") version ("7.3.1") apply false
    id("com.android.library") version("7.3.1") apply false
    id("org.jetbrains.kotlin.android") version("1.7.20") apply false
}

buildscript {
    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath("com.android.tools.build:gradle:7.2.2")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.20")
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.5.3")
    }
}