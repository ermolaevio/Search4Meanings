package com.ermolaevio.search4meanings.di

import com.ermolaevio.search4meanings.ui.SearchWordFragment
import com.ermolaevio.search4meanings.viewModel.SearchWordViewModel
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilder {

    @ContributesAndroidInjector(modules = [SearchWordModule::class])
    abstract fun bindSearchWordFragment(): SearchWordFragment

}