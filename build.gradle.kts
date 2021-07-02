import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.5.10"
    application
}

group = "com.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.opencsv:opencsv:5.4")
    implementation("org.valiktor:valiktor-core:0.12.0")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.12.3")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-xml:2.12.3")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-csv:2.12.3")
    implementation("org.slf4j:slf4j-simple:1.7.31")
    implementation("org.slf4j:slf4j-api:1.7.31")
    implementation("io.github.microutils:kotlin-logging:2.0.8")
    implementation("commons-cli:commons-cli:1.4")
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "11"
}

application {
    mainClassName = "MainKt"
}