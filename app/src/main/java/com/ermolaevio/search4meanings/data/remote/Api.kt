package com.ermolaevio.search4meanings.data.remote

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

// TODO(FIX) добавить описание методов
interface Api {

    @GET("/api/public/v1/words/search")
    fun searchWords(@Query("ids") ids: String): Single<List<FoundWordDto>>

    @GET("/api/public/v1/meanings")
    fun getMeanings(@Query("id") id: String): Single<List<MeaningDto>>

}