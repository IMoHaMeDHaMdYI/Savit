plugins {
    id(BuildPlugins.androidLibrary)
    kotlin(BuildPlugins.android)
    kotlin(BuildPlugins.androidExtension)
    kotlin(BuildPlugins.kapt)
}

android {
    signingConfigs {
        create("release") {
            storeFile = file("savit-key.jks")
            storePassword = "123123"
            keyAlias = "key0"
            keyPassword = "123123"
        }
    }

    buildToolsVersion("30.0.3")
    compileSdkVersion(30)

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.4.31")
    implementation(Dependencies.coreCore)
    implementation("androidx.appcompat:appcompat:1.2.0")
    implementation("com.google.android.material:material:1.3.0")
    implementation("androidx.constraintlayout:constraintlayout:2.0.4")

    implementation(Dependencies.daggerRuntime)
    kapt(Dependencies.daggerCompiler)

    api(Dependencies.roomRuntime)
    kapt(Dependencies.roomCompiler)
    implementation(Dependencies.roomExt)
    implementation(Dependencies.roomRx)


    implementation(Dependencies.rxJava)
    implementation(Dependencies.rxAndroid)
    implementation(Dependencies.rxKotlin)
//    implementation(Dependencies.roomCoroutines)

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.2")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.3.0")
}