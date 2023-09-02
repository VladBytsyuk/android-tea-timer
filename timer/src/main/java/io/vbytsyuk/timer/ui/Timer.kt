package io.vbytsyuk.timer.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.vbytsyuk.domain.Time

@Composable
fun Timer(
    time: Time,
    modifier: Modifier = Modifier,
) = Box(modifier = modifier) {
    Row {
        TimeBlock(charsPair = time.ceilMinuteStrings)
        TimeCharText(char = ':')
        TimeBlock(charsPair = time.ceilSecondStrings)
    }
}

@Composable
private fun TimeBlock(
    charsPair: Pair<Char, Char>,
    modifier: Modifier = Modifier,
) = Row(modifier = modifier) {
    TimeCharText(char = charsPair.first)
    TimeCharText(char = charsPair.second)
}

@Composable
private fun TimeCharText(
    char: Char,
) = Text(
    text = char.toString(),
    modifier = Modifier.padding(4.dp),
)

private val PREVIEW_TIME = Time(minutes = 12, seconds = 34)

@Preview
@Composable
private fun Timer_preview() = Timer(PREVIEW_TIME)
