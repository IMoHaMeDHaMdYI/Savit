package com.savit

import android.app.Application
import com.savit.di.AppComponentHolder
import com.savit.di.AppFeatureDependencies
import com.savit.module_injection.*

class SavitApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        AppComponentHolder.dependencyProvider = {
            class AppComponentDependencyHolder(
                override val block: (BaseDependencyHolder<AppFeatureDependencies>, List<BaseFeatureAPI>) -> AppFeatureDependencies
            ) : DependencyHolderList<BaseFeatureAPI, AppFeatureDependencies>(listOf())

            AppComponentDependencyHolder { dependenciesHolder, features ->
                object : AppFeatureDependencies {
                    override val context = this@SavitApplication
                    override val dependencyHolder = dependenciesHolder
                }
            }.dependencies
        }

        AppComponentHolder.get()
    }
}