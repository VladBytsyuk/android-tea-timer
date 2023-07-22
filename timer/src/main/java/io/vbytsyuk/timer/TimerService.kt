package io.vbytsyuk.timer

import io.vbytsyuk.domain.Time
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface TimerService {
    val timeFlow: Flow<Time>
    val stateFlow: Flow<State>

    fun start(time: Time)
    fun pause()
    fun resume()
    fun reset()

    enum class State { IDLE, RUNNING, PAUSED }
}
