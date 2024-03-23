plugins {
    kotlin("jvm") version "1.9.21"
    id("org.jlleitschuh.gradle.ktlint") version "12.0.3"
}

group = "me.bossm0n5t3r.coroutines"
version = "1.0-SNAPSHOT"

private val kotlinxCoroutineVersion = "1.8.0"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test:1.9.21")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$kotlinxCoroutineVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactive:$kotlinxCoroutineVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor:$kotlinxCoroutineVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-rx3:$kotlinxCoroutineVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:$kotlinxCoroutineVersion")

    implementation("io.projectreactor:reactor-core:3.6.3")

    implementation("org.assertj:assertj-core:3.25.2")

    implementation("io.mockk:mockk:1.13.9")

    testImplementation("app.cash.turbine:turbine:1.1.0")
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
