package com.ermolaevio.search4meanings.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ermolaevio.search4meanings.domain.execution.ThreadScheduler
import com.ermolaevio.search4meanings.domain.execution.scheduleIoToUi
import com.ermolaevio.search4meanings.domain.interactor.SearchMeaningInteractor
import com.ermolaevio.search4meanings.domain.interactor.SearchMeaningInteractorImpl
import com.ermolaevio.search4meanings.ui.DEBOUNCE_DEFAULT
import com.ermolaevio.search4meanings.ui.models.WordAndMeaningViewItem
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit

class SearchWordViewModel(
    private val interactor: SearchMeaningInteractor,
    private val scheduler: ThreadScheduler
) : ViewModel() {

    // TODO придумать как обьединить фабрики, чтобы небыло мусора в каждой vm
    class SearchWordViewModelFactory(
        private val interactor: SearchMeaningInteractorImpl,
        private val scheduler: ThreadScheduler
    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return SearchWordViewModel(interactor, scheduler) as T
        }
    }

    private val result = MutableLiveData<List<WordAndMeaningViewItem>>(emptyList())
    val loading = MutableLiveData<Boolean>(false)
    private val empty = MutableLiveData<Boolean>(false)

    val loadingLiveData: LiveData<Boolean> = loading
    val emptyLiveData: LiveData<Boolean> = empty
    val resultLiveData: LiveData<List<WordAndMeaningViewItem>> = result

    private val searchSubject = PublishSubject.create<String>()
    private val disposables = CompositeDisposable()

    init {
        initSearchObservable()
    }

    fun search(text: String) {
        searchSubject.onNext(text)
    }

    private fun initSearchObservable() {
        searchSubject
            .debounce(DEBOUNCE_DEFAULT, TimeUnit.MILLISECONDS, scheduler.io())
            .doOnNext(::checkIfEmpty)
            .filter(String::isNotBlank)
            .distinctUntilChanged()
            .doOnNext { loading.postValue(true) }
            .switchMapSingle { interactor.search(it) }
            .map(WordAndMeaningViewItem.Companion::create)
            .scheduleIoToUi(scheduler)
            .subscribeBy {
                loading.value = false
                result.value = it
                empty.value = it.isEmpty()
            }
            .addTo(disposables)
    }

    private fun checkIfEmpty(it: String) {
        if (it.isBlank()) {
            loading.postValue(false)
            result.postValue(emptyList())
            empty.postValue(false)
        }
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }
}