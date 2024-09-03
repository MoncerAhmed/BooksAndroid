plugins {
    id("java-library")
    alias(libs.plugins.jetbrains.kotlin.jvm)
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    implementation(libs.inject)

    testImplementation(kotlin("test"))
    testImplementation(libs.test.mockk)
    testImplementation(libs.test.kotlinx.coroutines)
}

tasks.test {
    useJUnitPlatform()
}
