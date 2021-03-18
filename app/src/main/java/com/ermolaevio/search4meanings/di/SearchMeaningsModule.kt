package com.ermolaevio.search4meanings.di

import com.ermolaevio.search4meanings.data.remote.Api
import com.ermolaevio.search4meanings.data.repository.SearchMeaningsRepositoryImpl
import com.ermolaevio.search4meanings.domain.execution.ThreadScheduler
import com.ermolaevio.search4meanings.domain.interactor.SearchMeaningInteractorImpl
import com.ermolaevio.search4meanings.domain.repository.SearchMeaningsRepository
import com.ermolaevio.search4meanings.viewModel.MeaningViewModel
import com.ermolaevio.search4meanings.viewModel.SearchWordViewModel
import dagger.Module
import dagger.Provides

@Module
class SearchMeaningsModule {

    @Provides
    fun provideRepository(api: Api): SearchMeaningsRepository {
        return SearchMeaningsRepositoryImpl(api)
    }

    @Provides
    fun provideSearchWordViewModelFactory(
        interactor: SearchMeaningInteractorImpl,
        scheduler: ThreadScheduler
    ) = SearchWordViewModel.SearchWordViewModelFactory(
        interactor,
        scheduler
    )

    @Provides
    fun provideMeaningViewModelFactory(
        interactor: SearchMeaningInteractorImpl,
        scheduler: ThreadScheduler
    ) = MeaningViewModel.MeaningViewModelFactory(
        interactor,
        scheduler
    )
}