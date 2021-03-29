package com.savit.dashboard.presentation.view

import android.view.LayoutInflater
import com.savit.core.base.view.BaseActivity
import com.savit.core.di.provideCoreComponent
import com.savit.core.di.scopedComponent
import com.savit.dashboard.R
import com.savit.dashboard.databinding.ActivitySavitBinding
import com.savit.dashboard.di.DaggerDashboardComponent
import com.savit.dashboard.di.DashboardComponent

class SavitActivity : BaseActivity<ActivitySavitBinding>() {
    override val theme: Int = R.style.Savit
    override val graph: Int = R.navigation.savit_nav_graph
    override val navHostId: Int = R.id.savitNavHost

    private val component: DashboardComponent by scopedComponent {
        DaggerDashboardComponent.factory().create(provideCoreComponent(this.applicationContext))
    }

    override fun onPerformInjection() {
        component.inject(this)
    }

    override fun onCreateBinding(inflater: LayoutInflater): ActivitySavitBinding {
        return ActivitySavitBinding.inflate(inflater)
    }
}
