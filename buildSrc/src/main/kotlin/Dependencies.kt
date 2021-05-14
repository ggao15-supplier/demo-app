const val kotlinVersion = "1.3.72"

object BuildPlugins {

    object Versions {
        const val buildToolsVersion = "3.3.1"
    }

    const val androidGradlePlugin = "com.android.tools.build:gradle:${Versions.buildToolsVersion}"
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion"
    const val androidApplication = "com.android.application"
    const val androidLibrary = "com.android.library"
    const val kotlinAndroid = "kotlin-android"
    const val kotlinApt = "kotlin-kapt"
}

object AndroidSDK {
    const val minSDK = 24
    const val compileSDK = 30
    const val targetSDK = compileSDK
    const val versionCode = 1
    const val versionName = "1.0"
    const val androidJunitRunner = "androidx.test.runner.AndroidJUnitRunner"
    const val applicationId = "com.ggg.demo"
    const val jdkVersion = 1.8
}

object Versions {
    const val androidx_appcompat_lib = "1.1.0"
    const val androidx_lifecycle_ktx_lib = "2.2.0"
    const val androidx_core_ktx_lib = "1.2.0"
    const val kotlinx_coroutines_lib = "1.3.7"
    const val androidx_constraintlayout_lib = "1.1.3"
    const val androidx_recyclerview_lib = "1.1.0"
    const val retrofit2_lib = "2.8.1"
    const val test_junit_lib = "4.12"
    const val test_runner_lib = "1.2.0"
    const val test_espresso_core_lib = "3.2.0"
    const val material_lib = "1.0.0"
}

object Libs {
    const val androidx_appcompat = "androidx.appcompat:appcompat:${Versions.androidx_appcompat_lib}"
    const val kotlin_sdk = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${kotlinVersion}"
    const val androidx_core = "androidx.core:core-ktx:${Versions.androidx_core_ktx_lib}"
    const val androidx_lifecycle_runtime =
        "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.androidx_lifecycle_ktx_lib}"
    const val androidx_lifecycle_livedata =
        "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.androidx_lifecycle_ktx_lib}"
    const val androidx_lifecycle_viewmodel =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.androidx_lifecycle_ktx_lib}"
    const val androidx_lifecycle_reactivestreams =
        "androidx.lifecycle:lifecycle-reactivestreams-ktx:${Versions.androidx_lifecycle_ktx_lib}"
    const val kotlinx_coroutines =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.kotlinx_coroutines_lib}"
    const val androidx_constraintlayout =
        "androidx.constraintlayout:constraintlayout:${Versions.androidx_constraintlayout_lib}"
    const val androidx_recyclerview =
        "androidx.recyclerview:recyclerview:${Versions.androidx_recyclerview_lib}"
    const val retrofit2 = "com.squareup.retrofit2:retrofit:${Versions.retrofit2_lib}"
    const val test_junit_lib = "junit:junit:${Versions.test_junit_lib}"
    const val test_runner = "androidx.test:runner:${Versions.test_runner_lib}"
    const val test_espresso_core =
        "androidx.test.espresso:espresso-core:${Versions.test_espresso_core_lib}"
    const val material_lib = "com.google.android.material:material:${Versions.material_lib}"
}