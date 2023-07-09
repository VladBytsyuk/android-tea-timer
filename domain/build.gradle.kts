plugins {
    id(Config.Plugin.JAVA_LIBRARY)
    id(Config.Plugin.KOTLIN_JVM)
}

java {
    sourceCompatibility = Config.JVM.VERSION
    targetCompatibility = Config.JVM.VERSION
}
