plugins {
    id(BuildPlugins.kotlin)
    id(BuildPlugins.kapt)
}

dependencies {
    implementation(Libs.kotlinStd)

    implementation(Libs.retrofit)
    implementation(Libs.retrofitMoshiConverter)

    implementation(Libs.okHttp)
    implementation(Libs.moshi)
    implementation(Libs.okio)

    implementation(Libs.dagger)
    kapt(Libs.daggerCompiler)

    implementation(Libs.threeTenAbp)

    testImplementation(TestLibs.jUnit)
    testImplementation(TestLibs.kotlinTest)
    testImplementation(TestLibs.mockito)

    testImplementation ("org.threeten:threetenbp:1.4.0"){
        exclude("com.jakewharton.threetenabp", "threetenabp")
    }

    implementation(project(":domain"))
}