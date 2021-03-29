// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    val kotlin_version by extra("1.4.31")
    repositories {
        addRepos()
    }
    dependencies {
        classpath(BuildPlugins.classpathAndroidGradlePlugin)
        classpath(BuildPlugins.classpathKotlinPlugin)
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version")
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle.kts.kts files
    }
}

allprojects {
    repositories {
        addRepos()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}