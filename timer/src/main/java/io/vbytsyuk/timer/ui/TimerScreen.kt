package io.vbytsyuk.timer.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import io.vbytsyuk.domain.Time
import io.vbytsyuk.timer.service.TimerService

@Composable
fun TimerScreen(
    viewModel: TimerScreenViewModel = viewModel(factory = TimerScreenViewModel.Factory()),
) = TimerScreen(
    time = viewModel.time.collectAsState(initial = Time(minutes = 0, seconds = 5)).value,
    state = viewModel.state.collectAsState(initial = TimerService.State.IDLE).value,
    onButtonClick = viewModel::onButtonClick,
)

@Composable
fun TimerScreen(
    time: Time,
    state: TimerService.State = TimerService.State.IDLE,
    onButtonClick: () -> Unit = {},
) {
    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        Column(
            modifier = Modifier.align(Alignment.Center),
        ) {
            Timer(
                time = time,
                modifier = Modifier.align(Alignment.CenterHorizontally),
            )
            Button(
                onClick = onButtonClick,
            ) {
                val text = when (state) {
                    TimerService.State.IDLE -> "Start"
                    TimerService.State.RUNNING -> "Pause"
                    TimerService.State.PAUSED -> "Play"
                }
                Text(text = text)
            }
        }
    }
}

private val PREVIEW_TIME = Time(minutes = 12, seconds = 34)

@Preview(device = Devices.PHONE)
@Preview(device = Devices.TABLET)
@Preview(
    device = Devices.WEAR_OS_LARGE_ROUND,
    uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_WATCH,
)
@Composable
private fun TimerScreen_preview() = TimerScreen(PREVIEW_TIME)
