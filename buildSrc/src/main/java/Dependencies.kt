import org.gradle.api.artifacts.dsl.RepositoryHandler
import java.net.URI

const val kotlinVersion = "1.4.10"

fun RepositoryHandler.addRepos() {
    this.google()
    this.jcenter()
    this.maven { url = URI.create("https://jitpack.io") }
}


object BuildPlugins {
    const val minSdk = 24
    const val targetSdk = 30
    const val buildTools = "30.0.2"
    const val versoinName = "1.0"
    const val versionCode = 1


    object Version {
        const val androidGradlePlugin = "4.1.1"
        const val detekt = "1.10.0"
        const val spotless = "4.5.1"
        const val crashlytics = "2.2.0"
        const val cassPathNavigation = "2.3.0"

    }

    const val jvm = "jvm"

    const val android = "android"
    const val androidApplication = "com.android.application"
    const val androidLibrary = "com.android.library"
    const val androidLibraryPlugin = "android-library"
    const val androidExtension = "android.extensions"
    const val kotlinAndroid = "kotlin-android"
    const val kotlinAndroidExtensions = "kotlin-android-extensions"
    const val kotlinKapt = "kotlin-kapt"
    const val kapt = "kapt"

    const val safeArgs = "androidx.navigation.safeargs.kotlin"

    const val classpathAndroidGradlePlugin =
        "com.android.tools.build:gradle:${Version.androidGradlePlugin}"
    const val classpathKotlinPlugin =
        "org.jetbrains.kotlin:kotlin-gradle-plugin:${kotlinVersion}"
    const val classpathGms = "com.google.gms:google-services:4.3.3"
    const val classpathNavigationsafeargs =
        "androidx.navigation:navigation-safe-args-gradle-plugin:${Version.cassPathNavigation}"
    const val classpathCrashlytics =
        "com.google.firebase:firebase-crashlytics-gradle:${Version.crashlytics}"


}


object Version {
    const val atslCore = "1.2.0"
    const val atslJunit = "1.1.1"
    const val atslRules = "1.2.0"
    const val atslRunner = "1.2.0"
    const val truth = "1.0.1"
    const val mockito = "2.25.0"
    const val mockitoAll = "1.10.19"
    const val mockitoAndroid = "2.25.0"
    const val mockWebServer = "3.8.1"
    const val junit = "4.13"
    const val jetpack = "1.0.0-beta01"
    const val constraintLayout = "2.0.0-beta4"
    const val ktx = "1.1.0-alpha05"
    const val activity = "1.1.0"
    const val annotations = "1.0.0"
    const val apache_commons = "2.5"
    const val appcompat = "1.2.0"
    const val archCore = "2.1.0"
    const val cardview = "1.0.0"
    const val coreKtx = "1.3.2"
    const val benchmark = "1.0.0"
    const val coreUtils = "1.0.0"
    const val gmsVersion = "17.0.0"
    const val coroutines = "1.3.3"
    const val dagger = "2.27"

    const val daggerAssistedInject = "0.5.2"
    const val espresso = "3.2.0"
    const val fragment = "1.2.2"
    const val glide = "4.11.0"
    const val kotlin = "1.3.70"
    const val lifecycle = "2.2.0"
    const val material = "1.2.0"
    const val okhttpVersion = "4.4.0"
    const val recyclerview = "1.1.0"
    const val recyclerviewAlpha = "1.2.0-alpha05"
    const val swiperefreshlayout = "1.1.0-rc01"
    const val gson = "2.8.6"
    const val leakcanary = "2.1"
    const val robolectric = "4.2"
    const val room = "2.2.3"
    const val timber = "4.7.1"
    const val work = "2.5.0-beta01"
    const val viewpager2 = "1.0.0"
    const val crashlytics = "17.1.1"
    const val navigation = "2.3.0"
    const val supportAnnotation = "28.0.0"
    const val testRunner = "1.0.2"
    const val dynamicAnimationKtx = "1.0.0-alpha03"
}

object Dependencies {

    object Test {
        const val atslCore = "androidx.test:core:${Version.atslCore}"
        const val atslExtJunit = "androidx.test.ext:junit:${Version.atslJunit}"
        const val atslRunner = "androidx.test:runner:${Version.atslRunner}"

        const val atslRules = "androidx.test:rules:${Version.atslRules}"
        const val mockitoCore = "org.mockito:mockito-core:${Version.mockito}"
        const val mockitoAll = "org.mockito:mockito-all:${Version.mockitoAll}"
        const val mockitoAndroid = "org.mockito:mockito-android:${Version.mockitoAndroid}"

        const val mockWebServer = "com.squareup.okhttp3:mockwebserver:${Version.mockWebServer}"

        const val truth = "com.google.truth:truth:${Version.truth}"

        const val junit = "junit:junit:${Version.junit}"

        const val supportAnnotation =
            "com.android.support:support-annotations:${Version.supportAnnotation}"
        const val testRunner =
            "com.android.support.test:runner:${Version.testRunner}"

        const val espressoCore = "androidx.test.espresso:espresso-core:${Version.espresso}"
        const val espressoContrib = "androidx.test.espresso:espresso-contrib:${Version.espresso}"
        const val espressoIntents = "androidx.test.espresso:espresso-intents:${Version.espresso}"

        const val room = "androidx.room:room-testing:${Version.room}"

    }

    const val activityActivityKtx = "androidx.activity:activity-ktx:${Version.activity}"


    const val annotations = "androidx.annotation:annotation:${Version.annotations}"

    const val appCompat = "androidx.appcompat:appcompat:${Version.appcompat}"

    const val archCoreRuntime = "androidx.arch.core:core-runtime:${Version.archCore}"
    const val archCoreTesting = "androidx.arch.core:core-testing:${Version.archCore}"


    const val benchmark = "androidx.benchmark:benchmark-junit4:${Version.benchmark}"
    const val benchmarkGradle =
        "androidx.benchmark:benchmark-gradle-plugin:${Version.benchmark}"

    const val cardview = "androidx.cardview:cardview:${Version.cardview}"

    const val constraintLayout =
        "androidx.constraintlayout:constraintlayout:${Version.constraintLayout}"

    const val coreCore = "androidx.core:core:${Version.coreKtx}"
    const val coreKtx = "androidx.core:core-ktx:${Version.coreKtx}"
    const val coreUtils = "androidx.legacy:legacy-support-core-utils:${Version.coreUtils}"
    const val coreLegacySupportV4 = "androidx.legacy:legacy-support-v4:${Version.coreUtils}"


    const val coroutinesAndroid =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Version.coroutines}"
    const val coroutinesTest =
        "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Version.coroutines}"
    const val uiViewpager2 = "androidx.viewpager2:viewpager2:${Version.viewpager2}"

    const val daggerRuntime = "com.google.dagger:dagger:${Version.dagger}"
    const val daggerCompiler = "com.google.dagger:dagger-compiler:${Version.dagger}"

    const val assistedInjectAnnotations =
        "com.squareup.inject:assisted-inject-annotations-dagger2:${Version.daggerAssistedInject}"
    const val assistedInjectProcessor =
        "com.squareup.inject:assisted-inject-processor-dagger2:${Version.daggerAssistedInject}"

    const val fragmentRuntime = "androidx.fragment:fragment:${Version.fragment}"
    const val fragmentRuntimeKtx = "androidx.fragment:fragment-ktx:${Version.fragment}"
    const val fragmentTesting = "androidx.fragment:fragment-testing:${Version.fragment}"


    const val glideRuntime = "com.github.bumptech.glide:glide:${Version.glide}"
    const val glideCompiler = "com.github.bumptech.glide:compiler:${Version.glide}"

    const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion"
    const val kotlinTest = "org.jetbrains.kotlin:kotlin-test-junit:${kotlinVersion}"
    const val kotlinAllopen = "org.jetbrains.kotlin:kotlin-allopen:${kotlinVersion}"

    const val firebaseIid = "com.google.firebase:firebase-iid:20.1.1"
    const val firebaseAnalytics = "com.google.firebase:firebase-analytics-ktx"
    const val firebaseFirestore = "com.google.firebase:firebase-firestore"
    const val firebaseMessaging = "com.google.firebase:firebase-messaging"
    const val firebaseConfigsKtx = "com.google.firebase:firebase-config-ktx"
    const val firebaseCrashlytics =
        "com.google.firebase:firebase-crashlytics:${Version.crashlytics}"
    const val firebaseBoM = "com.google.firebase:firebase-bom:26.0.0"

    const val androidxTransition = "androidx.transition:transition:1.3.0"

    const val lifecycleRuntime = "androidx.lifecycle:lifecycle-runtime:${Version.lifecycle}"
    const val lifecycleViewModel = "androidx.lifecycle:lifecycle-viewmodel:${Version.lifecycle}"

    const val lifecycleJava8 = "androidx.lifecycle:lifecycle-common-java8:${Version.lifecycle}"
    const val lifecycleCompiler = "androidx.lifecycle:lifecycle-compiler:${Version.lifecycle}"
    const val lifecycleViewmodelKtx =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Version.lifecycle}"
    const val lifecycleLivedataKtx =
        "androidx.lifecycle:lifecycle-livedata-ktx:${Version.lifecycle}"
    const val lifecycleProcess = "androidx.lifecycle:lifecycle-process:${Version.lifecycle}"
    const val lifecycleReactivestreams =
        "androidx.lifecycle:lifecycle-reactivestreams:${Version.lifecycle}"
    const val lifecycleViewmodelSavedState =
        "androidx.lifecycle:lifecycle-viewmodel-savedstate:${Version.lifecycle}"

    const val material = "com.google.android.material:material:${Version.material}"

    const val navigationRuntime = "androidx.navigation:navigation-runtime:${Version.navigation}"
    const val navigationRuntimeKtx =
        "androidx.navigation:navigation-runtime-ktx:${Version.navigation}"
    const val navigationFragment = "androidx.navigation:navigation-fragment:${Version.navigation}"
    const val navigationFragmentKtx =
        "androidx.navigation:navigation-fragment-ktx:${Version.navigation}"
    const val navigationUi = "androidx.navigation:navigation-ui:${Version.navigation}"
    const val navigationUiKtx = "androidx.navigation:navigation-ui-ktx:${Version.navigation}"
    const val navigationSafeArgsPlugin =
        "androidx.navigation:navigation-safe-args-gradle-plugin:${Version.navigation}"
    const val navigationDynamicFeatures =
        "androidx.navigation:navigation-dynamic-features-fragment:${Version.navigation}"

    const val recyclerview = "androidx.recyclerview:recyclerview:${Version.recyclerview}"
    const val recyclerviewAlpha = "androidx.recyclerview:recyclerview:${Version.recyclerviewAlpha}"
    const val swiperefreshlayout =
        "androidx.swiperefreshlayout:swiperefreshlayout:${Version.swiperefreshlayout}"
    const val gson = "com.google.code.gson:gson:${Version.gson}"
    const val leakcanary = "com.squareup.leakcanary:leakcanary-android:${Version.leakcanary}"

    const val robolectric = "org.robolectric:robolectric:${Version.robolectric}"

    const val roomRuntime = "androidx.room:room-runtime:${Version.room}"
    const val roomCompiler = "androidx.room:room-compiler:${Version.room}"
    const val roomRxjava2 = "androidx.room:room-rxjava2:${Version.room}"

    const val timber = "com.jakewharton.timber:timber:${Version.timber}"

    const val dynamicAnimationKtx =
        "androidx.dynamicanimation:dynamicanimation-ktx:${Version.dynamicAnimationKtx}"

    const val work = "androidx.work:work-runtime-ktx:${Version.work}"
}