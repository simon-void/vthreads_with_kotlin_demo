
plugins {
    kotlin("jvm") version "1.9.20"
    java
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

kotlin {
    jvmToolchain(21)
}

sourceSets {
    main {
        java {
            srcDirs(
                "src/main/kotlin/"
            )
        }
    }
}

tasks.jar {
    manifest {
        attributes("Main-Class" to "MainKt")
    }

    // To add all of the dependencies
    from(sourceSets.main.get().output)

    dependsOn(configurations.runtimeClasspath)
    from ({
        configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) }
    })
}
