package com.ermolaevio.search4meanings.data.remote

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

// TODO(FIX) добавить описание методов
interface Api {

    @GET("/api/public/v1/words/search")
    fun searchWords(
        @Query("search") search: String,
        /*@Query("page") page: Int?,
        @Query("pageSize") pageSize: Int?*/
    ): Single<List<FoundWordDto>>

    @GET("/api/public/v1/meanings")
    fun getMeanings(
        @Query("ids") ids: String,
        @Query("updatedAt") updatedAt: Long?
    ): Single<List<MeaningDto>>

}