package com.ermolaevio.search4meanings.domain.execution

import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

class TrampolineThreadScheduler : ThreadScheduler() {

    val scheduler = Schedulers.trampoline()

    override fun ui(): Scheduler = scheduler
    override fun io(): Scheduler = scheduler
    override fun computation(): Scheduler = scheduler

}