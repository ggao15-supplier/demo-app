const val kotlinVersion = "1.3.21"

object BuildPlugins {

    object Versions {
        const val buildToolsVersion = "3.3.1"
    }

    const val androidGradlePlugin = "com.android.tools.build:gradle:${Versions.buildToolsVersion}"
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion"
    const val androidApplication = "com.android.application"
    const val kotlinAndroid = "kotlin-android"
    const val kotlinAndroidExtensions = "kotlin-android-extensions"

}

object AndroidSdk {
    const val min = 15
    const val compile = 28
    const val target = compile
}

object Versions {
    val androidx_appcompat_lib = "1.1.0"
    val androidx_lifecycle_ktx_lib = "2.2.0"
    val androidx_core_ktx_lib = "1.2.0"
    val kotlinx_coroutines_lib = "1.3.7"
    val androidx_constraintlayout_lib = "1.1.3"
    val androidx_recyclerview_lib = "1.1.0"
    val retrofit2_lib = "2.8.1"
}

object Libs {
    val androidx_appcompat = "androidx.appcompat:appcompat:${Versions.androidx_appcompat_lib}"
    val androidx_core = "androidx.core:core-ktx:${Versions.androidx_core_ktx_lib}"
    val androidx_lifecycle_runtime =
        "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.androidx_lifecycle_ktx_lib}"
    val androidx_lifecycle_livedata =
        "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.androidx_lifecycle_ktx_lib}"
    val androidx_lifecycle_viewmodel =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.androidx_lifecycle_ktx_lib}"
    val androidx_lifecycle_reactivestreams =
        "androidx.lifecycle:lifecycle-reactivestreams-ktx:${Versions.androidx_lifecycle_ktx_lib}"
    val kotlinx_coroutines =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.kotlinx_coroutines_lib}"
    val androidx_constraintlayout =
        "androidx.constraintlayout:constraintlayout:${Versions.androidx_constraintlayout_lib}"
    val androidx_recyclerview =
        "androidx.recyclerview:recyclerview:${Versions.androidx_recyclerview_lib}"
    val retrofit2 = "com.squareup.retrofit2:retrofit:${Versions.retrofit2_lib}"
}