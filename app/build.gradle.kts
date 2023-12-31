import io.gitlab.arturbosch.detekt.Detekt
import io.gitlab.arturbosch.detekt.DetektCreateBaselineTask

plugins {
    id(Config.Plugin.ANDROID)
    id(Config.Plugin.KOTLIN)
    id(Config.Plugin.DETEKT)
}

android {
    namespace = Config.NAMESPACE
    compileSdk = Config.SDK.COMPILE

    defaultConfig {
        applicationId = "io.vbytsyuk.tea"
        minSdk = Config.SDK.MIN
        targetSdk = Config.SDK.COMPILE
        versionCode = Config.Version.CODE
        versionName = Config.Version.NAME

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = Config.Proguard.IS_MINIFY
            proguardFiles(getDefaultProguardFile(Config.Proguard.OPTIMIZE), Config.Proguard.RULES)
        }
    }
    compileOptions {
        sourceCompatibility = Config.JVM.VERSION
        targetCompatibility = Config.JVM.VERSION
    }
    kotlinOptions {
        jvmTarget = Config.JVM.TARGET
    }
    buildFeatures {
        compose = Config.Compose.IS_ENABLED
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Config.Compose.COMPILER_VERSION
    }
    packagingOptions {
        resources {
            excludes += Config.EXCLUDED_PACKAGES
        }
    }
}

dependencies {
    implementation(
        project(Modules.UI_KIT),
        project(Modules.TIMER),
        project(Modules.DOMAIN),
    )

    implementation(
        Deps.Android.CORE_KTX,
        Deps.Android.LIFECYCLE_RUNTIME_KTX,

        Deps.Android.Compose.ACTIVITY,
        Deps.Android.Compose.UI,
        Deps.Android.Compose.UI_GRAPHICS,
        Deps.Android.Compose.UI_TOOLING_PREVIEW,
        Deps.Android.Compose.MATERIAL_3,

        platform(Deps.Android.Compose.BOM),
        platform(Deps.Kotlin.BOM),
    )

    testImplementation(
        Deps.Test.JUNIT,
    )

    androidTestImplementation(
        Deps.Test.Android.ESPRESSO,
        Deps.Test.Android.JUNIT_EXT,
        Deps.Test.Android.JUNIT_COMPOSE,

        platform(Deps.Android.Compose.BOM),
    )

    debugImplementation(
        Deps.Debug.Compose.TOOLING,
        Deps.Debug.Compose.TEST_MANIFEST,
    )

    detektPlugins(
        Deps.Detekt.FORMATTING,
    )
}

detekt {
    source.setFrom(
        "../app/src/main/java",
        "../core/src/main/java",
        "../domain/src/main/java",
        "../timer/src/main/java",
        "../uikit/src/main/java",
    )
    buildUponDefaultConfig = true
    allRules = false
    config.setFrom("$projectDir/config/detekt.yml")
    baseline = file("$projectDir/config/baseline.xml")
}

tasks.withType<Detekt>().configureEach {
    reports {
        html.required.set(true)
        md.required.set(true)
    }
}

tasks.withType<Detekt>().configureEach {
    jvmTarget = Config.JVM.TARGET
}
tasks.withType<DetektCreateBaselineTask>().configureEach {
    jvmTarget = Config.JVM.TARGET
}

fun DependencyHandlerScope.implementation(vararg dependencies: Any) =
    dependencies.forEach(::implementation)

fun DependencyHandlerScope.androidTestImplementation(vararg dependencies: Any) =
    dependencies.forEach(::androidTestImplementation)

fun DependencyHandlerScope.debugImplementation(vararg dependencies: Any) =
    dependencies.forEach(::debugImplementation)
