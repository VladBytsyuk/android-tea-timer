package io.vbytsyuk.timer

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import io.vbytsyuk.domain.Time

@Composable
fun Timer(time: Time) {
    Text(text = "${time.minutes}:${time.seconds}")
}

private val PREVIEW_TIME = Time(minutes = 12, seconds = 34)

@Preview(
    device = Devices.PHONE,
)
@Composable
private fun Timer_phone() = Timer(PREVIEW_TIME)

@Preview(
    device = Devices.TABLET,
)
@Composable
private fun Timer_tablet() = Timer(PREVIEW_TIME)

@Preview(
    device = Devices.WEAR_OS_LARGE_ROUND,
)
@Composable
private fun Timer_wear() = Timer(PREVIEW_TIME)
