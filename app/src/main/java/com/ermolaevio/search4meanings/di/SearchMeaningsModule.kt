package com.ermolaevio.search4meanings.di

import com.ermolaevio.search4meanings.AppRouter
import com.ermolaevio.search4meanings.data.remote.Api
import com.ermolaevio.search4meanings.data.repository.SearchMeaningsRepositoryImpl
import com.ermolaevio.search4meanings.domain.interactor.SearchMeaningInteractor
import com.ermolaevio.search4meanings.domain.repository.SearchMeaningsRepository
import com.ermolaevio.search4meanings.viewModel.MeaningViewModel
import com.ermolaevio.search4meanings.viewModel.SearchWordViewModel
import dagger.Module
import dagger.Provides
import io.reactivex.android.schedulers.AndroidSchedulers

@Module
class SearchMeaningsModule {

    @Provides
    fun provideRepository(api: Api): SearchMeaningsRepository {
        return SearchMeaningsRepositoryImpl(api)
    }

    @Provides
    fun provideSearchWordViewModelFactory(
        interactor: SearchMeaningInteractor
    ): SearchWordViewModel.SearchWordViewModelFactory {
        return SearchWordViewModel.SearchWordViewModelFactory(
            interactor,
            AppRouter(),
            AndroidSchedulers.mainThread()
        )
    }

    @Provides
    fun provideMeaningViewModelFactory(
        interactor: SearchMeaningInteractor
    ): MeaningViewModel.MeaningViewModelFactory {
        return MeaningViewModel.MeaningViewModelFactory(
            interactor,
            AndroidSchedulers.mainThread()
        )
    }
}