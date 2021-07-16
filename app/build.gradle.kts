plugins {
    id(BuildPlugins.androidApplication)
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
    buildFeatures {
        viewBinding = true
    }
    buildToolsVersion("30.0.2")
    compileSdkVersion(30)

    defaultConfig {
        applicationId = "com.savit"
        minSdkVersion(24)
        targetSdkVersion(30)
        versionCode = 1
        versionName = "1.0"

//        testInstrumentationRunner "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            minifyEnabled(false)
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("release")
        }

        getByName("debug") {
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(project(":local"))
    implementation(project(":module-injection"))
    implementation(project(":core"))
    implementation(project(":Dashboard"))

    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.4.31")
    implementation(Dependencies.coreCore)
    implementation(Dependencies.activityActivityKtx)

    implementation(Dependencies.daggerRuntime)
    kapt(Dependencies.daggerCompiler)

    implementation(Dependencies.glideRuntime)
    kapt(Dependencies.glideRuntime)
//    implementation("androidx.core:core-ktx:1.3.2")
    implementation("androidx.appcompat:appcompat:1.2.0")
    implementation("com.google.android.material:material:1.3.0")
    implementation("androidx.constraintlayout:constraintlayout:2.0.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.2")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.3.0")
}