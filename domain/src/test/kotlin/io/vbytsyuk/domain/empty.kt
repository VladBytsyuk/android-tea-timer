package io.vbytsyuk.domain

import io.kotest.core.spec.style.FeatureSpec
import io.kotest.matchers.shouldBe

internal fun FeatureSpec.featureIsNotEmpty() = feature("Time object empty state") {
    feature("empty time") {
        scenario("constructed with milliseconds") {
            Time(milliseconds = 0L).isNotEmpty() shouldBe false
        }
        scenario("constructed with minutes & seconds") {
            Time(minutes = 0, seconds = 0).isNotEmpty() shouldBe false
        }
    }
    feature("non-empty time") {
        scenario("constructed with milliseconds") {
            Time(milliseconds = 1L).isNotEmpty() shouldBe true
        }
        scenario("constructed with minutes & seconds") {
            Time(minutes = 0, seconds = 1).isNotEmpty() shouldBe true
        }
    }
}
