plugins {
    id("java-library")
    alias(libs.plugins.jetbrains.kotlin.jvm)
    alias(libs.plugins.kotlin.kapt)
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    api(project(":data:dal:api"))
    implementation(project(":common:kotlin"))
    implementation(project(":templates:kotlin"))
    implementation(project(":data:network:api"))
    implementation(project(":data:storage:api"))

    // Dagger
    implementation(libs.dagger.dagger)
    kapt(libs.dagger.compiler)
}
