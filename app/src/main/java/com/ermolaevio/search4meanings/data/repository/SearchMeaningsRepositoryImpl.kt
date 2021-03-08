package com.ermolaevio.search4meanings.data.repository

import com.ermolaevio.search4meanings.convert
import com.ermolaevio.search4meanings.data.remote.Api
import com.ermolaevio.search4meanings.domain.models.FoundWord
import com.ermolaevio.search4meanings.domain.models.Meaning
import com.ermolaevio.search4meanings.domain.repository.SearchMeaningsRepository
import io.reactivex.Single

class SearchMeaningsRepositoryImpl(private val api: Api) : SearchMeaningsRepository {

    override fun search(search: String): Single<List<FoundWord>> {
        return api.searchWords(search).convert()
    }

    override fun getMeaningDetail(ids: Array<String>): Single<List<Meaning>> {
        return api.getMeanings(
            ids = ids.joinToString(),
            updatedAt = null
        ).convert()
    }
}