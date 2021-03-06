package com.ermolaevio.search4meanings.domain.repository

import com.ermolaevio.search4meanings.domain.models.FoundWord
import com.ermolaevio.search4meanings.domain.models.Meaning
import io.reactivex.Single

interface SearchMeaningsRepository {

    fun search(search: String): Single<List<FoundWord>>

    fun getMeaningDetail(ids: Array<String>): Single<List<Meaning>>
}