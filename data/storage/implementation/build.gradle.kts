plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.ksp)
}

android {
    namespace = "nl.ahmed.data.storage.implementation"

    ksp {
        arg("room.generateKotlin", "true")
    }
}

dependencies {
    implementation(project(":core:api"))
    implementation(project(":common:android"))
    implementation(project(":common:kotlin"))
    api(project(":data:storage:api"))

    // Dagger
    implementation(libs.bundles.dagger)
    kapt(libs.bundles.dagger.kapt)

    // Room
    implementation(libs.data.room)
    implementation(libs.data.room.ktx)
    ksp(libs.data.room.compiler)

    implementation(libs.androidx.core.ktx)
}
