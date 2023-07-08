object Deps {
    object Android {
        const val CORE_KTX = "androidx.core:core-ktx:1.10.1"
        const val LIFECYCLE_RUNTIME_KTX = "androidx.lifecycle:lifecycle-runtime-ktx:2.6.1"

        object Compose {
            const val UI = "androidx.compose.ui:ui"
            const val UI_GRAPHICS = "androidx.compose.ui:ui-graphics"
            const val UI_TOOLING_PREVIEW = "androidx.compose.ui:ui-tooling-preview"
            const val MATERIAL_3 = "androidx.compose.material3:material3"
            const val ACTIVITY = "androidx.activity:activity-compose:1.7.2"
            const val BOM = "androidx.compose:compose-bom:2022.10.00"
        }
    }

    object Kotlin {
        const val BOM = "org.jetbrains.kotlin:kotlin-bom:1.8.0"
    }

    object Detekt {
        const val FORMATTING = "io.gitlab.arturbosch.detekt:detekt-formatting:${Config.Plugin.DETEKT_VERSION}"
    }

    object Test {
        const val JUNIT = "junit:junit:4.13.2"

        object Android {
            const val JUNIT_EXT = "androidx.test.ext:junit:1.1.5"
            const val JUNIT_COMPOSE = "androidx.compose.ui:ui-test-junit4"
            const val ESPRESSO = "androidx.test.espresso:espresso-core:3.5.1"
        }
    }

    object Debug {
        object Compose {
            const val TOOLING = "androidx.compose.ui:ui-tooling"
            const val TEST_MANIFEST = "androidx.compose.ui:ui-test-manifest"
        }
    }
}
