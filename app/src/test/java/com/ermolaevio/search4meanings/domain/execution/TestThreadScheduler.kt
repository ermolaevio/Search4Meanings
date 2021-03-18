package com.ermolaevio.search4meanings.domain.execution

import io.reactivex.Scheduler
import io.reactivex.schedulers.TestScheduler

class TestThreadScheduler : ThreadScheduler() {

    val scheduler = TestScheduler()

    override fun ui(): Scheduler {
        return scheduler
    }

    override fun io(): Scheduler {
        return scheduler
    }

    override fun computation(): Scheduler {
        return scheduler
    }
}