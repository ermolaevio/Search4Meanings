package com.ermolaevio.search4meanings.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ermolaevio.search4meanings.AppRouter
import com.ermolaevio.search4meanings.domain.interactor.SearchMeaningInteractor
import com.ermolaevio.search4meanings.domain.models.FoundWord
import javax.inject.Inject

class SearchWordViewModel(
    private val interactor: SearchMeaningInteractor,
    private val router: AppRouter
) : ViewModel() {

    class SearchWordViewModelFactory @Inject constructor(
        private val interactor: SearchMeaningInteractor,
        private val router: AppRouter
    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return SearchWordViewModel(interactor, router) as T
        }
    }

    val result = MutableLiveData<List<FoundWord>>(emptyList())
    val loading = MutableLiveData<Boolean>(false)



}