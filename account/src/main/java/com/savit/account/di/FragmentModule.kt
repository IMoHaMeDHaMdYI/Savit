package com.savit.account.di

import androidx.fragment.app.Fragment
import com.savit.account.presentation.view.AccountsListFragment
import com.savit.account.presentation.view.AddAccountFragment
import com.savit.core.di.scope.FragmentKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface FragmentModule {

    @Binds
    @IntoMap
    @FragmentKey(AddAccountFragment::class)
    fun bindAddAccountFragment(bind: AddAccountFragment): Fragment

    @Binds
    @IntoMap
    @FragmentKey(AccountsListFragment::class)
    fun bindAccountsListFragment(bind: AccountsListFragment): Fragment
}