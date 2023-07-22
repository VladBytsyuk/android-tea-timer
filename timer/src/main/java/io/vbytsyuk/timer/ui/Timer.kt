package io.vbytsyuk.timer.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import io.vbytsyuk.domain.Time

@Composable
fun Timer(
    time: Time,
    modifier: Modifier = Modifier,
) = Box(modifier = modifier) {
    Text(
        text = time.toString(),
    )
}

private val PREVIEW_TIME = Time(minutes = 12, seconds = 34)

@Preview
@Composable
private fun Timer_preview() = Timer(PREVIEW_TIME)
