package com.savit.record.di

import androidx.fragment.app.Fragment
import com.savit.core.di.scope.FragmentKey
import com.savit.record.presentation.view.AddRecordFragment
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface FragmentModule {

    @Binds
    @IntoMap
    @FragmentKey(AddRecordFragment::class)
    fun bindAddRecordFragment(bind: AddRecordFragment): Fragment
}