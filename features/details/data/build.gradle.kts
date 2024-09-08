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
    implementation(project(":data:dal:api"))
    implementation(project(":features:details:domain:api"))
    implementation(project(":common:kotlin"))
    implementation(project(":templates:kotlin"))

    implementation(libs.dagger.dagger)
    kapt(libs.dagger.compiler)
}
