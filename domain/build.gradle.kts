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
        Deps.Test.KoTest.FRAMEWORK,
        Deps.Test.KoTest.ASSERTIONS,
        Deps.Test.KoTest.PROPERTY,
        Deps.Test.KoTest.DATASET,
    )
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}

fun DependencyHandlerScope.testImplementation(vararg dependencies: Any) =
    dependencies.forEach(::testImplementation)
