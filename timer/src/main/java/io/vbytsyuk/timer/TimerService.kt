package io.vbytsyuk.timer

import io.vbytsyuk.domain.Time
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface TimerService {
    val timeFlow: Flow<Time>
    val time: Time

    val stateFlow: Flow<State>
    val state: State

    fun start(time: Time)
    fun pause()
    fun resume()
    fun reset()

    enum class State { IDLE, RUNNING, PAUSED }
}
