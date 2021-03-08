package com.ermolaevio.search4meanings.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ermolaevio.search4meanings.domain.interactor.SearchMeaningInteractor
import com.ermolaevio.search4meanings.domain.models.Meaning
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers

class MeaningViewModel(
    private val id: String,
    private val interactor: SearchMeaningInteractor,
    private val schedulers: Scheduler
) : ViewModel() {

    class MeaningViewModelFactory(
        private val interactor: SearchMeaningInteractor,
        private val schedulers: Scheduler,
        var id: String = ""
    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return MeaningViewModel(id, interactor, schedulers) as T
        }
    }

    val meaningInfo = MutableLiveData<Meaning>()
    private val disposables = CompositeDisposable()

    init {
        interactor.getMeaning(arrayOf(id))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                meaningInfo.value = it.first()
            }, {
                // TODO(Fix) что с ошибкой делать
            })
            .addTo(disposables)
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }
}