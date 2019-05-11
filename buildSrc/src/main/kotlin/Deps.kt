const val kotlinVersion = "1.3.31"

object BuildPlugins {

    private object Versions {
        const val androidGradlePlugin = "3.4.0"
    }

    const val androidGradlePlugin = "com.android.tools.build:gradle:${Versions.androidGradlePlugin}"
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion"
    const val androidApplication = "com.android.application"
    const val kotlin = "kotlin"
    const val kotlinAndroid = "kotlin-android"
    const val kapt = "kotlin-kapt"
}

object Libs {

    private object Versions {
        const val androidX = "1.0.0"
        const val fragment = "1.1.0-alpha08"
        const val lifecycle = "2.2.0-alpha01"

        const val material = "1.0.0"
        const val constraintLayout = "2.0.0-alpha2"

        const val coroutines = "1.2.1"
        const val dagger = "2.22.1"

        const val retrofit = "2.5.0"
        const val okHttp = "4.0.0-alpha01"
        const val okio = "2.2.2"
        const val moshi = "1.8.0"

        const val threeTenAbp = "1.2.0"
        const val mpCharts = "v3.1.0"
    }

    const val kotlinStd = "org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion"

    const val androidXCore = "androidx.core:core:${Versions.androidX}"
    const val fragment = "androidx.fragment:fragment:${Versions.fragment}"

    const val lifecycle = "androidx.lifecycle:lifecycle-runtime:${Versions.lifecycle}"
    const val lifecycleExtensions = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle}"
    const val lifecycleViewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"

    const val material = "com.google.android.material:material:${Versions.material}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"

    const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"

    const val dagger = "com.google.dagger:dagger:${Versions.dagger}"
    const val daggerCompiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"
    const val daggerAndroid = "com.google.dagger:dagger-android-support:${Versions.dagger}"
    const val daggerAndroidCompiler = "com.google.dagger:dagger-android-processor:${Versions.dagger}"

    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofitMoshiConverter = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"

    const val okHttp = "com.squareup.okhttp3:okhttp:${Versions.okHttp}"
    const val okHttpLoggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okHttp}"

    const val okio = "com.squareup.okio:okio:${Versions.okio}"
    const val moshi = "com.squareup.moshi:moshi-kotlin:${Versions.moshi}"

    const val threeTenAbp = "com.jakewharton.threetenabp:threetenabp:${Versions.threeTenAbp}"
    const val mpCharts = "com.github.PhilJay:MPAndroidChart:${Versions.mpCharts}"
}

object TestLibs {

    private object Versions {
        const val jUnit = "4.12"
        const val mockito = "1.5.0"
        const val robolectric = "4.2.1"
    }

    const val jUnit = "junit:junit:${Versions.jUnit}"
    const val kotlinTest = "org.jetbrains.kotlin:kotlin-test-junit:$kotlinVersion"
    const val mockito = "com.nhaarman:mockito-kotlin:${Versions.mockito}"
    const val robolectric = "org.robolectric:robolectric:${Versions.robolectric}"
}
