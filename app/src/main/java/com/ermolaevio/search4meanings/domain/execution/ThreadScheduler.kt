package com.ermolaevio.search4meanings.domain.execution

import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

open class ThreadScheduler @Inject constructor() {

    open fun ui(): Scheduler = AndroidSchedulers.mainThread()
    open fun io(): Scheduler = Schedulers.io()
    open fun computation(): Scheduler = Schedulers.computation()

    fun <T> ioToUiSingle() = { upstream: Single<T> -> upstream.subscribeOn(io()).observeOn(ui()) }

    fun <T> ioToUiObservable() =
        { upstream: Observable<T> -> upstream.subscribeOn(io()).observeOn(ui()) }

}

fun <T> Single<T>.scheduleIoToUi(scheduler: ThreadScheduler): Single<T> {
    return compose(scheduler.ioToUiSingle())
}

fun <T> Observable<T>.scheduleIoToUi(scheduler: ThreadScheduler): Observable<T> {
    return compose(scheduler.ioToUiObservable())
}

