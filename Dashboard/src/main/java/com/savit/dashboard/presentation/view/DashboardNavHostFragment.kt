package com.savit.dashboard.presentation.view

import com.savit.core.base.view.InjectingNavHostFragment
import com.savit.core.di.activityScopedComponent
import com.savit.dashboard.di.DashboardComponent

class DashboardNavHostFragment : InjectingNavHostFragment() {
    override fun onPerformInjection() {
        activityScopedComponent<DashboardComponent>().inject(this)
    }
}