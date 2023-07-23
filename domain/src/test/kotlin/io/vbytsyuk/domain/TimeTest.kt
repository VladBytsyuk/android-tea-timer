package io.vbytsyuk.domain

import io.kotest.core.spec.style.FeatureSpec

class TimeTest : FeatureSpec({
    featureTimeConstruction()
    featureTimePrint()
    featureIsNotEmpty()
})
