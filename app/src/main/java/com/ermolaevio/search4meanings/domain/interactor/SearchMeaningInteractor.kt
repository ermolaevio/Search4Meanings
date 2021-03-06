package com.ermolaevio.search4meanings.domain.interactor

import com.ermolaevio.search4meanings.domain.models.FoundWord
import com.ermolaevio.search4meanings.domain.models.Meaning
import com.ermolaevio.search4meanings.domain.repository.SearchMeaningsRepository
import io.reactivex.Single
import javax.inject.Inject

class SearchMeaningInteractor @Inject constructor(
    private val repository: SearchMeaningsRepository
) {

    fun search(search: String): Single<List<FoundWord>> = repository.search(search)

    fun getMeaning(ids: Array<String>): Single<List<Meaning>> = repository.getMeaningDetail(ids)

}