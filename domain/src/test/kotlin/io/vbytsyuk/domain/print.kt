package io.vbytsyuk.domain

import io.kotest.core.spec.style.FeatureSpec
import io.kotest.core.spec.style.scopes.FeatureSpecContainerScope
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

internal fun FeatureSpec.featureTimePrint() = feature("Time object conversion to string") {
    testMinutesSecondsConstructorFloorPrint()
    testMinutesSecondsConstructorCeilPrint()
    testMillisecondsConstructorFloorPrint()
    testMillisecondsConstructorCeilPrint()
}

private suspend fun FeatureSpecContainerScope.testMinutesSecondsConstructorFloorPrint() =
    feature("floor print constructed with minutes and seconds") {
        withData(
            nameFn = { (m, s, expected) -> "$m m $s s should be $expected" },
            Triple(0, 0, "00:00"),
            Triple(0, 1, "00:01"),
            Triple(0, 10, "00:10"),
            Triple(0, 59, "00:59"),
            Triple(1, 0, "01:00"),
            Triple(1, 1, "01:01"),
            Triple(1, 10, "01:10"),
            Triple(1, 59, "01:59"),
            Triple(10, 0, "10:00"),
            Triple(10, 1, "10:01"),
            Triple(10, 10, "10:10"),
            Triple(10, 59, "10:59"),
            Triple(59, 0, "59:00"),
            Triple(59, 1, "59:01"),
            Triple(59, 10, "59:10"),
            Triple(59, 59, "59:59"),
            Triple(60, 0, "60:00"),
            Triple(60, 1, "60:01"),
            Triple(60, 10, "60:10"),
            Triple(60, 59, "60:59"),
        ) { (m, s, expected) -> Time(minutes = m, seconds = s).toFloorString() shouldBe expected }
    }

private suspend fun FeatureSpecContainerScope.testMinutesSecondsConstructorCeilPrint() =
    feature("ceil print constructed with minutes and seconds") {
        withData(
            nameFn = { (m, s, expected) -> "$m m $s s should be $expected" },
            Triple(0, 0, "00:00"),
            Triple(0, 1, "00:01"),
            Triple(0, 10, "00:10"),
            Triple(0, 59, "00:59"),
            Triple(1, 0, "01:00"),
            Triple(1, 1, "01:01"),
            Triple(1, 10, "01:10"),
            Triple(1, 59, "01:59"),
            Triple(10, 0, "10:00"),
            Triple(10, 1, "10:01"),
            Triple(10, 10, "10:10"),
            Triple(10, 59, "10:59"),
            Triple(59, 0, "59:00"),
            Triple(59, 1, "59:01"),
            Triple(59, 10, "59:10"),
            Triple(59, 59, "59:59"),
            Triple(60, 0, "60:00"),
            Triple(60, 1, "60:01"),
            Triple(60, 10, "60:10"),
            Triple(60, 59, "60:59"),
        ) { (m, s, expected) -> Time(minutes = m, seconds = s).toCeilString() shouldBe expected }
    }

private suspend fun FeatureSpecContainerScope.testMillisecondsConstructorFloorPrint() =
    feature("floor print constructed with milliseconds") {
        withData(
            nameFn = { (millis, expected) -> "$millis ms should be $expected" },
            0L to "00:00",

            1L to "00:00",
            500L to "00:00",
            999L to "00:00",
            1_000L to "00:01",

            1_001L to "00:01",
            1_999L to "00:01",
            2_000L to "00:02",

            2_001L to "00:02",
            2_999L to "00:02",
            3_000L to "00:03",

            10_000L to "00:10",
            59_000L to "00:59",

            59_999L to "00:59",
            60_000L to "01:00",
            600_000L to "10:00",

            3_540_000L to "59:00",
            3_599_000L to "59:59",
            3_600_000L to "60:00",
        ) { (millis, expected) -> Time(milliseconds = millis).toFloorString() shouldBe expected }
    }

private suspend fun FeatureSpecContainerScope.testMillisecondsConstructorCeilPrint() =
    feature("ceil print constructed with milliseconds") {
        withData(
            nameFn = { (millis, expected) -> "$millis ms should be $expected" },
            0L to "00:00",

            1L to "00:01",
            500L to "00:01",
            999L to "00:01",
            1_000L to "00:01",

            1_001L to "00:02",
            1_999L to "00:02",
            2_000L to "00:02",

            2_001L to "00:03",
            2_999L to "00:03",
            3_000L to "00:03",

            10_000L to "00:10",
            59_000L to "00:59",

            59_999L to "01:00",
            60_000L to "01:00",
            600_000L to "10:00",

            3_540_000L to "59:00",
            3_599_000L to "59:59",
            3_600_000L to "60:00",
        ) { (millis, expected) -> Time(milliseconds = millis).toCeilString() shouldBe expected }
    }
