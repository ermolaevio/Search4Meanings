package com.ermolaevio.search4meanings

import com.ermolaevio.search4meanings.data.Dto
import io.reactivex.Observable
import io.reactivex.Single

fun <NetworkT : Dto<AppT>, AppT> Single<List<NetworkT>>.convert(): Single<List<AppT>> {
    return flatMap { list -> Single.just(list.map { it.convert() }) }
}

fun <T, R : Any> Observable<List<T>>.mapList(mapper: (T) -> R?): Observable<List<R>> {
    return map { list -> list.mapNotNull { mapper.invoke(it) } }
}