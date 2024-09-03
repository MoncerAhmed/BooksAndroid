plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
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
    ksp(libs.bundles.dagger.ksp)

    // Room
    implementation(libs.data.room)
    implementation(libs.data.room.ktx)
    ksp(libs.data.room.compiler)

    implementation(libs.androidx.core.ktx)
}
