package com.savit.dashboard.di.module

import androidx.fragment.app.Fragment
import com.savit.core.di.scope.FragmentKey
import com.savit.dashboard.presentation.view.DashboardFragment
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface FragmentModule {

    @Binds
    @IntoMap
    @FragmentKey(DashboardFragment::class)
    fun bindsDashboardFragment(bind: DashboardFragment): Fragment
}
