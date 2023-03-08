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
    }

    dependencies {
        compileOnly("io.papermc.paper:paper-api:1.19.2-R0.1-SNAPSHOT")
    }

    java {
        toolchain.languageVersion.set(JavaLanguageVersion.of(17))
    }
}

subprojects {
    tasks.test {
        useJUnitPlatform()
    }
}
