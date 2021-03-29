package com.savit.core.base.view

import android.os.Bundle
import android.view.LayoutInflater
import androidx.annotation.IdRes
import androidx.annotation.NavigationRes
import androidx.annotation.StyleRes
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<VB : ViewBinding> :
    AppCompatActivity() {

    lateinit var binding: VB
        private set

    @get:StyleRes
    protected abstract val theme: Int

    @get:NavigationRes
    abstract val graph: Int

    @get:IdRes
    abstract val navHostId: Int

    abstract fun onPerformInjection()

    abstract fun onCreateBinding(inflater: LayoutInflater): VB


    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(theme)
        onPerformInjection()

        super.onCreate(savedInstanceState)

        binding = onCreateBinding(layoutInflater)
        setContentView(binding.root)
    }
}