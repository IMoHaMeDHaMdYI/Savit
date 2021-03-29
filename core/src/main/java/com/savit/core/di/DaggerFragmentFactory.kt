package com.savit.core.di

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import javax.inject.Inject
import javax.inject.Provider

class DaggerFragmentFactory @Inject constructor(
    private val providers: Map<Class<out Fragment>, @JvmSuppressWildcards Provider<Fragment>>
) : FragmentFactory() {
    
    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        // loadFragmentClass is a static method of FragmentFactory
        // and will return the Class of the fragment
        val fragmentClass = loadFragmentClass(classLoader, className)

        // we will then be able to use fragmentClass to get the provider
        // of the Fragment from the providers map
        val provider = providers[fragmentClass]

        if (provider != null) {
            return provider.get()
        }

        // The provider for the fragment could be null
        // if the Fragment class is not binded to the Daggers graph
        // in this case, we will default to the default implementation
        // which will attempt to instantiate the Fragment 
        // through the no-arg constructor
        return super.instantiate(classLoader, className)
    }
}