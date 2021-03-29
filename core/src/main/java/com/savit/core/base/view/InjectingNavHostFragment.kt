package com.savit.core.base.view

import android.content.Context
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.savit.core.di.DaggerFragmentFactory
import javax.inject.Inject

abstract class InjectingNavHostFragment : NavHostFragment() {

    @Inject
    lateinit var fragmentFactory: DaggerFragmentFactory

    abstract fun onPerformInjection()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        onPerformInjection()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        childFragmentManager.fragmentFactory = fragmentFactory
        super.onCreate(savedInstanceState)
    }
}
