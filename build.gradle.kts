// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        addRepos()
    }
    dependencies {
        classpath(BuildPlugins.classpathAndroidGradlePlugin)
        classpath(BuildPlugins.classpathKotlinPlugin)
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
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