package com.ermolaevio.search4meanings.domain.interactor

import android.util.Log
import com.ermolaevio.search4meanings.domain.models.Meaning
import com.ermolaevio.search4meanings.domain.repository.SearchMeaningsRepository
import io.reactivex.Single
import javax.inject.Inject

class SearchMeaningInteractorImpl @Inject constructor(
    private val repository: SearchMeaningsRepository
) : SearchMeaningInteractor {

    override fun search(search: String) =
        // TODO(Fix) что то мне этот onErrorItem не оч =(
        repository.search(search).doOnError {
            Log.d("SearchMeaningInteractor", it.toString())
        }
            .onErrorReturnItem(emptyList())

    override fun getMeaning(ids: Array<String>): Single<List<Meaning>> =
        repository.getMeaningDetail(ids)

}