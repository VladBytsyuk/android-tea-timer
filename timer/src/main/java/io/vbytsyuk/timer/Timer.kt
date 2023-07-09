package io.vbytsyuk.timer

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun Timer() {
    Text(text = "Stub!")
}

@Preview(
    device = Devices.PHONE,
)
@Composable
private fun Timer_phone() = Timer()

@Preview(
    device = Devices.TABLET,
)
@Composable
private fun Timer_tablet() = Timer()

@Preview(
    device = Devices.WEAR_OS_LARGE_ROUND,
)
@Composable
private fun Timer_wear() = Timer()
