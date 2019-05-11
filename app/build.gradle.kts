plugins {
    id(BuildPlugins.androidApplication)
    id(BuildPlugins.kotlinAndroid)
    id(BuildPlugins.kapt)
}

android {
    compileSdkVersion(28)
    defaultConfig {
        applicationId = "com.kiliian.schibstedtest"
        versionCode = 1
        versionName = "1.0"

        minSdkVersion(21)
        targetSdkVersion(28)
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            isUseProguard = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }

    sourceSets {
        getByName("main").java.srcDirs("src/main/kotlin")
        getByName("test").java.srcDirs("src/test/kotlin")
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    implementation(Libs.kotlinStd)
    implementation(Libs.coroutines)
    implementation(Libs.coroutinesAndroid)

    implementation(Libs.androidXCore)
    implementation(Libs.fragment)

    implementation(Libs.lifecycle)
    implementation(Libs.lifecycleExtensions)
    implementation(Libs.lifecycleViewModelKtx)

    implementation(Libs.material)
    implementation(Libs.constraintLayout)

    implementation(Libs.dagger)
    kapt(Libs.daggerCompiler)
    implementation(Libs.daggerAndroid)
    kapt(Libs.daggerAndroidCompiler)

    implementation(Libs.retrofit)
    implementation(Libs.okHttp)
    implementation(Libs.okHttpLoggingInterceptor)
    implementation(Libs.moshi)

    implementation(Libs.threeTenAbp)
    implementation(Libs.mpCharts)

    testImplementation(TestLibs.jUnit)
    testImplementation(TestLibs.kotlinTest)
    testImplementation(TestLibs.mockito)
    testImplementation(TestLibs.robolectric)

    testImplementation ("org.threeten:threetenbp:1.4.0"){
        exclude("com.jakewharton.threetenabp", "threetenabp")
    }

    implementation(project(":domain"))
    implementation(project(":data"))
}