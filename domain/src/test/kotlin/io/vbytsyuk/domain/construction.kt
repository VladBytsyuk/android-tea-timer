package io.vbytsyuk.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FeatureSpec
import io.kotest.core.spec.style.scopes.FeatureSpecContainerScope
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

internal fun FeatureSpec.featureTimeConstruction() = feature("Time object construction") {
    testValidConstructionCases()
    testInvalidConstructionCases()
    testMinutesSecondsConstruction()
}


private suspend fun FeatureSpecContainerScope.testValidConstructionCases() =
    feature("construct valid object from") {
        testValidConstructionCasesWithMillis()
        testValidConstructionCasesWithMinutesAndSeconds()
    }

private suspend fun FeatureSpecContainerScope.testValidConstructionCasesWithMillis() =
    feature("from millis") {
        withData(
            nameFn = { "$it ms" },
            0L,
            Long.MAX_VALUE,
        ) {
            Time(milliseconds = it)
        }
    }

private suspend fun FeatureSpecContainerScope.testValidConstructionCasesWithMinutesAndSeconds() =
    feature("from minutes and seconds") {
        withData(
            nameFn = { "${it.first}m ${it.second}s" },
            0 to 0,
            0 to 1,
            0 to 59,
            1 to 0,
            59 to 0,
            60 to 0,
        ) { (minute, second) ->
            Time(minutes = minute, seconds = second)
        }
    }


private suspend fun FeatureSpecContainerScope.testInvalidConstructionCases() =
    feature("construct invalid object from") {
        testInvalidConstructionCasesWithMillis()
        testInvalidConstructionCasesWithMinutesAndSeconds()
    }

private suspend fun FeatureSpecContainerScope.testInvalidConstructionCasesWithMillis() =
    feature("from millis") {
        withData(
            nameFn = { "$it ms" },
            -1L,
            Long.MIN_VALUE,
        ) {
            shouldThrow<IllegalArgumentException> { Time(milliseconds = it) }
        }
    }

private suspend fun FeatureSpecContainerScope.testInvalidConstructionCasesWithMinutesAndSeconds() =
    feature("from minutes and seconds") {
        withData(
            nameFn = { "${it.first}m ${it.second}s" },
            0 to -1,
            0 to 60,
            -1 to 0,
            -1 to -1,
        ) { (minute, second) ->
            shouldThrow<IllegalArgumentException> { Time(minutes = minute, seconds = second) }
        }
    }


private suspend fun FeatureSpecContainerScope.testMinutesSecondsConstruction() =
    feature("construct object from minutes & seconds") {
        withData(
            nameFn = { (minutes, seconds, millis) -> "$millis ms should be $minutes m $seconds s" },
            Triple(0, 0, 0L),
            Triple(0, 1, 1000L),
            Triple(0, 10, 10_000L),
            Triple(0, 59, 59_000L),
            Triple(1, 0, 60_000L),
            Triple(10, 0, 600_000L),
            Triple(59, 0, 3_540_000L),
            Triple(59, 59, 3_599_000L),
            Triple(60, 0, 3_600_000L),
        ) { (minutes, seconds, millis) ->
            Time(minutes = minutes, seconds = seconds).milliseconds shouldBe millis
        }
    }