package io.vbytsyuk.timer.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.vbytsyuk.domain.Time
import io.vbytsyuk.timer.service.TimerService
import io.vbytsyuk.timer.service.TimerServiceCoroutines

class TimerScreenViewModel(
    private val timerService: TimerService,
) : ViewModel() {
    val time = timerService.time
    val state = timerService.state

    fun onButtonClick() = when (timerService.currentState) {
        TimerService.State.IDLE -> timerService.start(Time(minutes = 0, seconds = 5))
        TimerService.State.RUNNING -> timerService.pause()
        TimerService.State.PAUSED -> timerService.resume()
    }

    class Factory : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return TimerScreenViewModel(
                timerService = TimerServiceCoroutines(),
            ) as T
        }
    }
}