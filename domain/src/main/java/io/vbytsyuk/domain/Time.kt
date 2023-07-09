package io.vbytsyuk.domain

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

    private val minute: Minute
        get() = Minute(value = milliseconds / MILLISECONDS_IN_SECOND / SECONDS_IN_MINUTE)

    private val second: Second
        get() = Second(value = milliseconds / MILLISECONDS_IN_SECOND % SECONDS_IN_MINUTE)

    override fun toString(): String = "$minute:$second"
}

@JvmInline
private value class Minute(val value: Int = 0) {
    constructor(value: Long) : this(value = value.toInt())

    init { require(value >= 0) }

    val milliseconds: Long
        get() = (value * SECONDS_IN_MINUTE * MILLISECONDS_IN_SECOND).toLong()

    override fun toString(): String = when {
        value < DECIMAL -> "0$value"
        else -> value.toString()
    }
}

@JvmInline
private value class Second(val value: Int = 0) {
    constructor(value: Long) : this(value = value.toInt())

    init { require(value in 0.. MAX_VALUE) }

    val milliseconds: Long
        get() = (value * MILLISECONDS_IN_SECOND).toLong()

    override fun toString(): String = when {
        value < DECIMAL -> "0$value"
        else -> value.toString()
    }
}

private const val MAX_VALUE = 59
private const val DECIMAL = 10
private const val SECONDS_IN_MINUTE = 60
private const val MILLISECONDS_IN_SECOND = 1_000
