package io.vbytsyuk.timer.service

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.core.spec.style.scopes.BehaviorSpecWhenContainerScope
import io.kotest.matchers.shouldBe
import io.vbytsyuk.domain.Time
import kotlinx.coroutines.delay

class TimerServiceCoroutinesTest : BehaviorSpec({
    given("a timer service coroutines implementation") {
        val service: TimerService = TimerServiceCoroutines()

        `when`("it is constructed") {
            thenStateShouldBe(service, TimerService.State.IDLE)
            thenTimeShouldBe(service, "00:00")
        }

        `when`("start it with 5 seconds") {
            service.start(Time(minutes = 0, seconds = 5))

            thenStateShouldBe(service, TimerService.State.RUNNING)
            thenTimeShouldBe(service, "00:05")
        }

        `when`("wait almost 1 second") {
            delay(950L)

            thenStateShouldBe(service, TimerService.State.RUNNING)
            thenTimeShouldBe(service, "00:04")
        }

        `when`("wait almost 1 second again") {
            delay(950L)

            thenStateShouldBe(service, TimerService.State.RUNNING)
            thenTimeShouldBe(service, "00:03")
        }

        `when`("pause it") {
            service.pause()

            thenStateShouldBe(service, TimerService.State.PAUSED)
            thenTimeShouldBe(service, "00:03")
        }

        `when`("wait almost 1 second after pause") {
            delay(950)

            thenStateShouldBe(service, TimerService.State.PAUSED)
            thenTimeShouldBe(service, "00:03")
        }

        `when`("resume it") {
            service.resume()

            thenStateShouldBe(service, TimerService.State.RUNNING)
            thenTimeShouldBe(service, "00:03")
        }

        `when`("reset it") {
            service.reset()

            thenStateShouldBe(service, TimerService.State.IDLE)
            thenTimeShouldBe(service, "00:00")
        }

        `when`("start it with 2 seconds") {
            service.start(Time(minutes = 0, seconds = 2))

            thenStateShouldBe(service, TimerService.State.RUNNING)
            thenTimeShouldBe(service, "00:02")
        }

        `when`("wait 2 seconds") {
            delay(2_000L)

            thenStateShouldBe(service, TimerService.State.IDLE)
            thenTimeShouldBe(service, "00:00")
        }
    }
})

private suspend fun BehaviorSpecWhenContainerScope.thenStateShouldBe(
    service: TimerService,
    expected: TimerService.State,
) = then("state should be $expected") {
    service.currentState shouldBe expected
}

private suspend fun BehaviorSpecWhenContainerScope.thenTimeShouldBe(
    service: TimerService,
    expected: String,
) = then("time should be $expected") {
    service.currentTime.toString() shouldBe expected
}
