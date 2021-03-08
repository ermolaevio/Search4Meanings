package com.ermolaevio.search4meanings.di

import com.ermolaevio.search4meanings.ui.SearchWordFragment
import com.ermolaevio.search4meanings.ui.meaning.MeaningFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilder {

    @ContributesAndroidInjector(modules = [SearchMeaningsModule::class])
    abstract fun bindSearchWordFragment(): SearchWordFragment

    @ContributesAndroidInjector(modules = [SearchMeaningsModule::class])
    abstract fun bindMeaningFragment(): MeaningFragment

}