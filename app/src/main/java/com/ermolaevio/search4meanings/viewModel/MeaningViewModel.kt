package com.ermolaevio.search4meanings.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ermolaevio.search4meanings.domain.execution.ThreadScheduler
import com.ermolaevio.search4meanings.domain.execution.scheduleIoToUi
import com.ermolaevio.search4meanings.domain.interactor.SearchMeaningInteractor
import com.ermolaevio.search4meanings.domain.models.Meaning
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo

class MeaningViewModel(
    private val id: String,
    private val interactor: SearchMeaningInteractor,
    private val scheduler: ThreadScheduler
) : ViewModel() {

    class MeaningViewModelFactory(
        private val interactor: SearchMeaningInteractor,
        private val scheduler: ThreadScheduler,
        var id: String = ""
    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return MeaningViewModel(id, interactor, scheduler) as T
        }
    }

    val meaningInfo = MutableLiveData<Meaning>()
    val loading = MutableLiveData<Boolean>(true)
    val empty = MutableLiveData<Boolean>(false)
    private val disposables = CompositeDisposable()

    init {
        interactor.getMeaning(arrayOf(id))
            .scheduleIoToUi(scheduler)
            .subscribe({
                meaningInfo.value = it.first()
                empty.value = false
                loading.value = false
            }, {
                loading.value = false
                empty.value = true
            })
            .addTo(disposables)
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }
}