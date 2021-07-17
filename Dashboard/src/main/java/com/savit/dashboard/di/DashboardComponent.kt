package com.savit.dashboard.di

import com.savit.account.di.AccountModule
import com.savit.core.di.CoreComponent
import com.savit.core.di.scope.AppScope
import com.savit.dashboard.di.module.FragmentModule
import com.savit.dashboard.presentation.view.DashboardFragment
import com.savit.dashboard.presentation.view.DashboardNavHostFragment
import com.savit.dashboard.presentation.view.SavitActivity
import com.savit.record.di.RecordModule
import dagger.Component

@AppScope
@Component(
    modules = [AccountModule::class, FragmentModule::class, RecordModule::class],
    dependencies = [CoreComponent::class]
)
interface DashboardComponent {

    fun inject(activity: SavitActivity)
    fun inject(fragment: DashboardFragment)
    fun inject(fragment: DashboardNavHostFragment)

    @Component.Factory
    fun interface Factory {
        fun create(coreComponent: CoreComponent): DashboardComponent
    }
}
