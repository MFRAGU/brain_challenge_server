plugins {
    kotlin("jvm") version "1.9.24"
}

group = "org.mfragu_domain"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(8)
}

dependencies {
    implementation("io.insert-koin:koin-core:3.5.6")
}
