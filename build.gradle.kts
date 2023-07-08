// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id(Config.Plugin.ANDROID) version(Config.Plugin.ANDROID_VERSION) apply false
    id(Config.Plugin.ANDROID_LIBRARY) version(Config.Plugin.ANDROID_VERSION) apply false
    id(Config.Plugin.KOTLIN) version(Config.Plugin.KOTLIN_VERSION) apply false
}