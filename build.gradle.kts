plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.ktlint)
}

group = "me.bossm0n5t3r.coroutines"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.reactive)
    implementation(libs.kotlinx.coroutines.reactor)
    implementation(libs.kotlinx.coroutines.rx3)
    implementation(libs.kotlinx.coroutines.test)

    implementation(libs.reactor.core)
    implementation(libs.assertj.core)
    implementation(libs.mockk)

    testImplementation(libs.kotlin.test)
    testImplementation(libs.turbine)
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(21)
}

ktlint {
    version.set("1.5.0")
}
