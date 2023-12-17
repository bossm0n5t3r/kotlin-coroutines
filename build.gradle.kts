plugins {
    kotlin("jvm") version "1.9.21"
    id("org.jlleitschuh.gradle.ktlint") version "12.0.3"
}

group = "me.bossm0n5t3r.coroutines"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}

ktlint {
    version.set("1.0.0")
}
