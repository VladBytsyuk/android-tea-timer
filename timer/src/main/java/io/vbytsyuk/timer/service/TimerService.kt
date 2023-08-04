package io.vbytsyuk.timer.service

import io.vbytsyuk.domain.Time
import kotlinx.coroutines.flow.StateFlow

interface TimerService {
    enum class State { IDLE, RUNNING, PAUSED }

    val time: StateFlow<Time>
    val state: StateFlow<State>

    val currentTime: Time get() = time.value
    val currentState: State get() = state.value

    fun start(time: Time)
    fun pause()
    fun resume()
    fun reset()
}
