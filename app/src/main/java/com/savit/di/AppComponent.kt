package com.savit.di

import android.content.Context
import com.savit.SavitApplication
import com.savit.local.di.LocalModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [LocalModule::class],
    dependencies = [AppFeatureDependencies::class]
)
@Singleton
interface AppComponent : AppFeatureApi {
    fun inject(application: SavitApplication)

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance applicationContext: Context,
            appFeatureDependencies: AppFeatureDependencies
        ): AppComponent
    }

    companion object {
        fun initAndGet(dependencies: AppFeatureDependencies): AppComponent {
            return DaggerAppComponent.factory()
                .create(dependencies.context, dependencies)
        }
    }
}