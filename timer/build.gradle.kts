plugins {
    id(Config.Plugin.ANDROID_LIBRARY)
    id(Config.Plugin.KOTLIN)
}

android {
    namespace = "io.vbytsyuk.timer"
    compileSdk = Config.SDK.COMPILE

    defaultConfig {
        minSdk = Config.SDK.MIN
        targetSdk = Config.SDK.COMPILE

        testInstrumentationRunner = Config.ANDROID_JUNIT_RUNNER
        consumerProguardFiles(Config.Proguard.CONSUMER_RULES)
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
}

dependencies {
    implementation(
        project(Modules.UI_KIT),
        project(Modules.DOMAIN),
        project(Modules.CORE),
    )

    implementation(
        Deps.Android.CORE_KTX,
        Deps.Android.LIFECYCLE_RUNTIME_KTX,

        Deps.Android.Compose.ACTIVITY,
        Deps.Android.Compose.UI,
        Deps.Android.Compose.UI_GRAPHICS,
        Deps.Android.Compose.UI_TOOLING_PREVIEW,
        Deps.Android.Compose.MATERIAL_3,
        Deps.Android.Compose.VIEW_MODEL,

        platform(Deps.Android.Compose.BOM),
        platform(Deps.Kotlin.BOM),
    )

    testImplementation(
        Deps.Test.JUNIT,
        Deps.Test.KoTest.FRAMEWORK,
        Deps.Test.KoTest.ASSERTIONS,
        Deps.Test.KoTest.PROPERTY,
        Deps.Test.KoTest.DATASET,
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
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}

fun DependencyHandlerScope.implementation(vararg dependencies: Any) =
    dependencies.forEach(::implementation)

fun DependencyHandlerScope.testImplementation(vararg dependencies: Any) =
    dependencies.forEach(::testImplementation)

fun DependencyHandlerScope.androidTestImplementation(vararg dependencies: Any) =
    dependencies.forEach(::androidTestImplementation)

fun DependencyHandlerScope.debugImplementation(vararg dependencies: Any) =
    dependencies.forEach(::debugImplementation)
