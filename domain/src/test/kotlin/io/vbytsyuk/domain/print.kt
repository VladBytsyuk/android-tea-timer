package io.vbytsyuk.domain

import io.kotest.core.spec.style.FeatureSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

internal fun FeatureSpec.featureTimePrint() = feature("Time object conversion to string") {
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
    ) { (m, s, expected) -> Time(minutes = m, seconds = s).toString() shouldBe expected }
}
