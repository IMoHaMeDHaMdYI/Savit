package com.savit.dashboard.di

import com.savit.account.di.AccountModule
import com.savit.dashboard.presentation.view.DashboardFragment
import com.savit.dashboard.presentation.view.SavitActivity
import com.savit.local.di.LocalModule
import dagger.Component

@Component(modules = [LocalModule::class, AccountModule::class])
interface DashboardComponent {

    fun inject(activity: SavitActivity)
    fun inject(fragment: DashboardFragment)

    @Component.Factory
    fun interface Factory {
        fun create(): DashboardComponent
    }
}
