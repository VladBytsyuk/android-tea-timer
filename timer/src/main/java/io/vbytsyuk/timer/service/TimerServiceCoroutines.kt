package io.vbytsyuk.timer.service

import io.vbytsyuk.domain.Time
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

internal class TimerServiceCoroutines : TimerService {
    private var lastCheckMs: Long = 0L
    private var timerJob: Job = SupervisorJob()

    private val _timeStateFlow: MutableStateFlow<Time> = MutableStateFlow(Time(milliseconds = 0L))
    override val timeFlow: Flow<Time> get() = _timeStateFlow
    override val time: Time get() = _timeStateFlow.value

    private var _stateFlow: MutableStateFlow<TimerService.State> = MutableStateFlow(TimerService.State.IDLE)
    override val stateFlow: Flow<TimerService.State> get() = _stateFlow
    override val state: TimerService.State get() = _stateFlow.value

    override fun start(time: Time) {
        saveState(TimerService.State.RUNNING)
        lastCheckMs = getSystemMs()
        _timeStateFlow.value = time
        runCoroutine()
    }

    override fun pause() {
        saveState(TimerService.State.PAUSED)
        lastCheckMs = 0L
        timerJob.cancel()
    }

    override fun resume() {
        saveState(TimerService.State.RUNNING)
        lastCheckMs = getSystemMs()
        runCoroutine()
    }

    override fun reset() {
        saveState(TimerService.State.IDLE)
        timerJob.cancel()
        if (time.isNotEmpty()) emitTime(ms = 0)
    }

    private fun runCoroutine() = CoroutineScope(Dispatchers.Default + timerJob).launch {
        while (time.isNotEmpty()) {
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
        val newTimeMs = time.milliseconds - diffMs
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
