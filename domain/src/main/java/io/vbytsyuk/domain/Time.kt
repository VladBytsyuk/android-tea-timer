package io.vbytsyuk.domain

import kotlin.math.ceil
import kotlin.math.floor

data class Time(
    val milliseconds: Long = 0L,
) {
    constructor(
        minutes: Int = 0,
        seconds: Int = 0,
    ) : this(
        milliseconds = Minute(minutes).milliseconds + Second(seconds).milliseconds,
    )

    init { require(milliseconds >= 0) }

    fun isNotEmpty(): Boolean = milliseconds != 0L

    override fun toString(): String = toFloorString()

    fun toFloorString(): String = "%02d:%02d".format(floorMinute.value, floorSecond.value)

    private val floorMinute: Minute
        get() = Minute(value = floor(rawSeconds).extractMinutes())

    private val floorSecond: Second
        get() = Second(value = floor(rawSeconds).extractSeconds())

    fun toCeilString(): String = "%02d:%02d".format(ceilMinute.value, ceilSecond.value)

    private val ceilMinute: Minute
        get() = Minute(value = ceil(rawSeconds).extractMinutes())

    private val ceilSecond: Second
        get() = Second(value = ceil(rawSeconds).extractSeconds())

    private val rawSeconds: Double
        get() = milliseconds.toDouble() / MILLISECONDS_IN_SECOND

    private fun Double.extractMinutes(): Int = (this / SECONDS_IN_MINUTE).toInt()

    private fun Double.extractSeconds(): Int = (this % SECONDS_IN_MINUTE).toInt()
}

@JvmInline
private value class Minute(val value: Int = 0) {
    init { require(value >= 0) }

    val milliseconds: Long
        get() = (value * SECONDS_IN_MINUTE * MILLISECONDS_IN_SECOND).toLong()
}

@JvmInline
private value class Second(val value: Int = 0) {
    init { require(value in 0 until SECONDS_IN_MINUTE) }

    val milliseconds: Long
        get() = (value * MILLISECONDS_IN_SECOND).toLong()
}

private const val SECONDS_IN_MINUTE = 60
private const val MILLISECONDS_IN_SECOND = 1_000
