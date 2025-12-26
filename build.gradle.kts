plugins {
    kotlin("jvm") version "1.8.0"
    application
    id("io.gitlab.arturbosch.detekt") version "1.23.3"
}

group = "mate.academy"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
    systemProperty("file.encoding", "UTF-8")
    jvmArgs("-Dfile.encoding=UTF-8")
}

// Не запускати detekt разом з build
tasks.named("check") {
    dependsOn.remove(tasks.named("detekt").get())
}

kotlin {
    jvmToolchain(11)
}

detekt {
    buildUponDefaultConfig = true
    allRules = false
    baseline = file("$projectDir/config/baseline.xml")
}