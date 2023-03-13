import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `java-library`

    kotlin("jvm") version "1.8.0"
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

allprojects {
    group = "scot.oskar.server"
    version = "0.0.1"

    apply(plugin = "java-library")
    apply(plugin = "kotlin")
    apply(plugin = "com.github.johnrengelman.shadow")

    repositories {
        mavenCentral()
        maven(url = "https://repo.papermc.io/repository/maven-public/")
        maven(url = "https://jitpack.io")
        maven(url = "https://storehouse.okaeri.eu/repository/maven-public/")
        maven(url = "https://repo.titanvale.net/releases")
    }

    dependencies {
        compileOnly("io.papermc.paper:paper-api:1.19.2-R0.1-SNAPSHOT")

        implementation("com.github.Revxrsal.Lamp:common:3.1.4")
        implementation("com.github.Revxrsal.Lamp:bukkit:3.1.4")
        implementation("com.github.Revxrsal.Lamp:brigadier:3.1.4")

        implementation("eu.okaeri:okaeri-configs-validator-okaeri:5.0.0-beta.4")
        implementation("eu.okaeri:okaeri-configs-serdes-bukkit:5.0.0-beta.4")
        implementation("eu.okaeri:okaeri-configs-serdes-commons:5.0.0-beta.4")
        implementation("eu.okaeri:okaeri-configs-yaml-bukkit:5.0.0-beta.4")

        implementation("dev.peri.yetanothermessageslibrary:core:5.0.0")
        implementation("dev.peri.yetanothermessageslibrary:repository-okaeri:5.0.0")
        implementation("dev.peri.yetanothermessageslibrary:platform-bukkit:5.0.0")

        implementation("org.postgresql:postgresql:42.5.4")
    }

    java {
        toolchain.languageVersion.set(JavaLanguageVersion.of(17))
    }

    tasks.named<JavaCompile>("compileJava") {
        options.compilerArgs.add("-parameters")
        options.isFork = true
        options.forkOptions.executable = "javac"
    }

    tasks.named<KotlinCompile>("compileKotlin") {
        kotlinOptions.javaParameters = true
    }
}

subprojects {
    tasks.test {
        useJUnitPlatform()
    }
}
