package com.savit.core.base.view

import android.os.Bundle
import android.view.View
import androidx.annotation.IdRes
import androidx.annotation.NavigationRes
import androidx.navigation.fragment.NavHostFragment
import androidx.viewbinding.ViewBinding
import com.savit.core.base.viewmodel.BaseViewModel
import com.savit.core.base.viewstate.ViewAction
import com.savit.core.base.viewstate.ViewEvent
import com.savit.core.base.viewstate.ViewState


abstract class BaseHostFragment<
        VS : ViewState,
        VE : ViewEvent,
        VA : ViewAction,
        VM : BaseViewModel<VS, VE, VA>,
        VB : ViewBinding,
        > : BaseFragment<VS, VE, VA, VM, VB>() {

    @get:NavigationRes
    abstract val graph: Int

    @get:IdRes
    abstract val navHostId: Int

    abstract val args: Bundle?

    @get:IdRes
    abstract val startDestId: Int

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val host = childFragmentManager.findFragmentById(navHostId) as NavHostFragment
        setupNavigationGraph(
            graphId = graph,
            host = host,
            startDestId = startDestId
        )
    }

    private fun setupNavigationGraph(
        @NavigationRes graphId: Int,
        host: NavHostFragment,
        @IdRes startDestId: Int
    ) {
        val inflater = host.navController.navInflater
        val graph = inflater.inflate(graphId)
        graph.startDestination = startDestId
        host.navController.setGraph(graph, args)
    }
}