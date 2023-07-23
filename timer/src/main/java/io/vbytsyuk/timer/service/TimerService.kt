package io.vbytsyuk.timer.service

import io.vbytsyuk.domain.Time
import kotlinx.coroutines.flow.Flow

interface TimerService {
    enum class State { IDLE, RUNNING, PAUSED }

    val timeFlow: Flow<Time>
    val time: Time

    val stateFlow: Flow<State>
    val state: State

    fun start(time: Time)
    fun pause()
    fun resume()
    fun reset()
}
