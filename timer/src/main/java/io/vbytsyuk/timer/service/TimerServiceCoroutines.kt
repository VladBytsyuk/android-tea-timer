package io.vbytsyuk.timer.service

import io.vbytsyuk.core.delegates.coroutines.reCreatableCoroutineScope
import io.vbytsyuk.core.delegates.coroutines.reCreatableSupervisorJob
import io.vbytsyuk.domain.Time
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

internal class TimerServiceCoroutines : TimerService {
    private var lastCheckMs: Long = 0L

    private val job by reCreatableSupervisorJob()
    private val scope by reCreatableCoroutineScope { Dispatchers.Default + job }

    private val _timeStateFlow: MutableStateFlow<Time> = MutableStateFlow(Time(milliseconds = 0L))
    override val time get() = _timeStateFlow

    private var _stateFlow: MutableStateFlow<TimerService.State> = MutableStateFlow(TimerService.State.IDLE)
    override val state get() = _stateFlow

    override fun start(time: Time) {
        saveState(TimerService.State.RUNNING)
        lastCheckMs = getSystemMs()
        _timeStateFlow.value = time
        runCoroutine()
    }

    override fun pause() {
        saveState(TimerService.State.PAUSED)
        lastCheckMs = 0L
        job.cancel()
    }

    override fun resume() {
        saveState(TimerService.State.RUNNING)
        lastCheckMs = getSystemMs()
        runCoroutine()
    }

    override fun reset() {
        saveState(TimerService.State.IDLE)
        job.cancel()
        if (currentTime.isNotEmpty()) emitTime(ms = 0)
    }

    private fun runCoroutine() = scope.launch {
        while (currentTime.isNotEmpty()) {
            delay(CHECK_DURATION_MS)
            ensureActive()
            check()
        }
        reset()
    }

    private fun check() {
        val currentMs = getSystemMs()
        val diffMs = currentMs - lastCheckMs
        lastCheckMs = currentMs
        val newTimeMs = currentTime.milliseconds - diffMs
        emitTime(ms = if (newTimeMs > 0L) newTimeMs else 0L)
    }

    private fun emitTime(ms: Long) {
        _timeStateFlow.value = Time(milliseconds = ms)
    }

    private fun saveState(state: TimerService.State) {
        _stateFlow.value = state
    }

    private fun getSystemMs(): Long = System.currentTimeMillis()

    companion object {
        private const val CHECK_DURATION_MS = 200L
    }
}
