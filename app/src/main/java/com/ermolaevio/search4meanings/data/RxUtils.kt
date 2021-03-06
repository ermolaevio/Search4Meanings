package com.ermolaevio.search4meanings.data

import io.reactivex.Single

fun <NetworkT : Dto<AppT>, AppT> Single<List<NetworkT>>.convert(): Single<List<AppT>> {
    return flatMap { list -> Single.just(list.map { it.convert() }) }
}