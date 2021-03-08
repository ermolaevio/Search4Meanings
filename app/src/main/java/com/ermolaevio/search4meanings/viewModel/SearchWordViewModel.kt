package com.ermolaevio.search4meanings.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ermolaevio.search4meanings.AppRouter
import com.ermolaevio.search4meanings.domain.interactor.SearchMeaningInteractor
import com.ermolaevio.search4meanings.domain.models.FoundWord
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit

class SearchWordViewModel(
    private val interactor: SearchMeaningInteractor,
    private val router: AppRouter,
    private val schedulers: Scheduler
) : ViewModel() {

    class SearchWordViewModelFactory constructor(
        private val interactor: SearchMeaningInteractor,
        private val router: AppRouter,
        private val schedulers: Scheduler
    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return SearchWordViewModel(interactor, router, schedulers) as T
        }
    }

    val result = MutableLiveData<List<FoundWord>>(emptyList())
    val loading = MutableLiveData<Boolean>(false)
    private val searchSubject = PublishSubject.create<String>()
    private val disposables = CompositeDisposable()

    init {
        searchSubject.debounce(300, TimeUnit.MILLISECONDS)
            .filter { it.isNotBlank() }
            .distinctUntilChanged()
            .doOnNext { loading.postValue(true) }
            .switchMapSingle { interactor.search(it) }
                // TODO(Fix) io ?
            .subscribeOn(Schedulers.io())
            .observeOn(schedulers)
            .subscribe({
                loading.value = false
                result.value = it
                Log.d("SearchWord", "next: ${it.size}")
            }, {
                loading.value = false
                result.value = emptyList()
                Log.d("SearchWord", "error: $it")
            })
            .addTo(disposables)
    }

    fun search(text: String) {
        searchSubject.onNext(text)
    }

    fun openMeaning(id: String) {
        router.openMeaning(id)
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }
}