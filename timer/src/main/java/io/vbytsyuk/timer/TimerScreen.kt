package io.vbytsyuk.timer

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import io.vbytsyuk.domain.Time

@Composable
fun TimerScreen(time: Time) {
    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        Timer(
            time = time,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

private val PREVIEW_TIME = Time(minutes = 12, seconds = 34)

@Preview(
    device = Devices.PHONE,
)
@Composable
private fun Timer_phone() = TimerScreen(PREVIEW_TIME)

@Preview(
    device = Devices.TABLET,
)
@Composable
private fun Timer_tablet() = TimerScreen(PREVIEW_TIME)

@Preview(
    device = Devices.WEAR_OS_LARGE_ROUND,
)
@Composable
private fun Timer_wear() = TimerScreen(PREVIEW_TIME)
