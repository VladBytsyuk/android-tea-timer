package io.vbytsyuk.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FeatureSpec
import io.kotest.core.spec.style.scopes.FeatureSpecContainerScope
import io.kotest.datatest.withData

internal fun FeatureSpec.featureTimeConstruction() = feature("Time object construction") {
    testValidConstructionCases()
    testInvalidConstructionCases()
}


private suspend fun FeatureSpecContainerScope.testValidConstructionCases() =
    feature("construct valid object from") {
        testValidConstructionCasesWithMillis()
        testValidConstructionCasesWithMinutesAndSeconds()
    }

private suspend fun FeatureSpecContainerScope.testValidConstructionCasesWithMillis() =
    feature("from millis") {
        withData(
            nameFn = { "$it ms." },
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
            nameFn = { "$it ms." },
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
        ) { (minute, second) ->
            shouldThrow<IllegalArgumentException> { Time(minutes = minute, seconds = second) }
        }
    }
