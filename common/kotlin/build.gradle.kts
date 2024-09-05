plugins {
    id("java-library")
    alias(libs.plugins.jetbrains.kotlin.jvm)
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    implementation(project(":templates:kotlin"))

    implementation(libs.inject)

    testImplementation(libs.junit)
    testImplementation(libs.test.mockk)
    testImplementation(libs.test.kotlinx.coroutines)
}

tasks.test {
    useJUnitPlatform()
}
