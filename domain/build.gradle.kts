plugins {
    id(Config.Plugin.JAVA_LIBRARY)
    id(Config.Plugin.KOTLIN_JVM)
}

java {
    sourceCompatibility = Config.JVM.VERSION
    targetCompatibility = Config.JVM.VERSION
}

dependencies {
    testImplementation(
        Deps.Test.JUNIT,
    )
}

fun DependencyHandlerScope.testImplementation(vararg dependencies: Any) =
    dependencies.forEach(::testImplementation)
