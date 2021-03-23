package com.savit.dashboard.di

import androidx.fragment.app.Fragment
import com.savit.dashboard.presentation.view.DashboardFragment
import com.savit.dashboard.presentation.view.SavitActivity
import com.savit.local.di.LocalModule
import dagger.Component

@Component(modules = [LocalModule::class])
interface DashboardComponent {

    fun inject(activity: SavitActivity)
    fun inject(fragment: DashboardFragment)

    @Component.Factory
    fun interface Factory {
        fun create(): DashboardComponent
    }
}