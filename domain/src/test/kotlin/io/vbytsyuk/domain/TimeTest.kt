package io.vbytsyuk.domain

import org.junit.Test

class TimeTest {

    @Test
    fun `construct time with correct (min) milliseconds`() {
        Time(milliseconds = 0)
    }

    @Test
    fun `construct time with correct (max) milliseconds`() {
        Time(milliseconds = Long.MAX_VALUE)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `construct time with incorrect milliseconds`() {
        Time(milliseconds = -1)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `construct time with correct minutes and incorrect (less) seconds`() {
        Time(minutes = 1, seconds = -1)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `construct time with correct minutes and incorrect (more) seconds`() {
        Time(minutes = 1, seconds = 60)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `construct time with incorrect minutes and correct seconds`() {
        Time(minutes = -1, seconds = 0)
    }

    @Test
    fun `construct time with correct minutes and correct seconds`() {
        Time(minutes = 1, seconds = 23)
    }
}
