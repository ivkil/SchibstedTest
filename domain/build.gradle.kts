plugins {
    id(BuildPlugins.kotlin)
    id(BuildPlugins.kapt)
}

dependencies {
    implementation(Libs.kotlinStd)
    implementation(Libs.threeTenAbp)

    implementation(Libs.dagger)
    kapt(Libs.daggerCompiler)

    testImplementation(TestLibs.jUnit)
    testImplementation(TestLibs.kotlinTest)
    testImplementation(TestLibs.mockito)

    testImplementation ("org.threeten:threetenbp:1.4.0"){
        exclude("com.jakewharton.threetenabp", "threetenabp")
    }
}