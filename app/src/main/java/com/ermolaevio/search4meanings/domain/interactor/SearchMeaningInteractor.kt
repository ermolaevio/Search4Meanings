package com.ermolaevio.search4meanings.domain.interactor

import com.ermolaevio.search4meanings.domain.models.FoundWord
import com.ermolaevio.search4meanings.domain.models.Meaning
import io.reactivex.Single

interface SearchMeaningInteractor {

    fun search(search: String): Single<List<FoundWord>>

    fun getMeaning(ids: Array<String>): Single<List<Meaning>>
}