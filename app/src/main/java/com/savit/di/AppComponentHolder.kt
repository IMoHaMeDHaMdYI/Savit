package com.savit.di

import android.content.Context
import com.savit.module_injection.BaseFeatureDependencies
import com.savit.module_injection.ComponentHolder
import com.savit.module_injection.ComponentHolderDelegate

object AppComponentHolder : ComponentHolder<AppFeatureApi, AppFeatureDependencies> {

    private val componentHolderDelegate =
        ComponentHolderDelegate<
                AppFeatureApi,
                AppFeatureDependencies,
                AppComponent> {
            AppComponent.initAndGet(it)
        }
    override var dependencyProvider: (() -> AppFeatureDependencies)? by componentHolderDelegate::dependencyProvider

    override fun get(): AppFeatureApi {
        return componentHolderDelegate.getComponentImpl()
    }
}

interface AppFeatureDependencies : BaseFeatureDependencies {
    val context: Context
}