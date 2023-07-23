package io.vbytsyuk.timer.service

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.vbytsyuk.domain.Time

class TimerServiceCoroutinesTest : BehaviorSpec({
    given("a timer service coroutines implementation") {
        val service: TimerService = TimerServiceCoroutines()

        `when`("it is constructed") {
            then("state should be IDLE") {
                service.state shouldBe TimerService.State.IDLE
            }
            then("time should be 00:00") {
                service.time.toString() shouldBe "00:00"
            }
        }

        `when`("start it with 5 seconds") {
            service.start(Time(minutes = 0, seconds = 5))

            then("state should be RUNNING") {
                service.state shouldBe TimerService.State.RUNNING
            }
        }

        `when`("pause it") {
            service.pause()

            then("state should be PAUSED") {
                service.state shouldBe TimerService.State.PAUSED
            }
        }

        `when`("resume it") {
            service.resume()

            then("state should be RUNNING") {
                service.state shouldBe TimerService.State.RUNNING
            }
        }

        `when`("reset it") {
            service.reset()

            then("state should be IDLE") {
                service.state shouldBe TimerService.State.IDLE
            }
        }
    }
})
