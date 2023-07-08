import org.gradle.api.JavaVersion

object Config {
    const val NAMESPACE = "io.vbytsyuk.tea"

    object SDK {
        const val COMPILE = 33
        const val MIN = 26
    }

    object Version {
        private const val MAJOR = 0
        private const val MINOR = 1
        private const val PATCH = 0

        const val NAME = "$MAJOR.$MINOR.$PATCH"
        const val CODE = 1_000_000 * MAJOR + 1_000 * MINOR + PATCH
    }

    object JVM {
        val VERSION = JavaVersion.VERSION_1_8
        const val TARGET = "1.8"
    }

    object Proguard {
        const val IS_MINIFY = false
        const val OPTIMIZE = "proguard-android-optimize.txt"
        const val RULES = "proguard-rules.pro"
        const val CONSUMER_RULES = "consumer-rules.pro"
    }

    const val ANDROID_JUNIT_RUNNER = "androidx.test.runner.AndroidJUnitRunner"
    const val EXCLUDED_PACKAGES = "/META-INF/{AL2.0,LGPL2.1}"

    object Plugin {
        const val ANDROID = "com.android.application"
        const val ANDROID_LIBRARY = "com.android.library"
        const val ANDROID_VERSION = "8.0.2"

        const val KOTLIN = "org.jetbrains.kotlin.android"
        const val KOTLIN_VERSION = "1.7.20"
    }

    object Compose {
        const val IS_ENABLED = true
        const val COMPILER_VERSION = "1.3.2"
    }
}
