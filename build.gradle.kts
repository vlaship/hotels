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

    testImplementation("com.jayway.jsonpath:json-path:2.6.0")
    testImplementation("org.mockito:mockito-inline:3.11.2")
    testImplementation("io.mockk:mockk:1.12.0")
    testImplementation("io.kotest:kotest-runner-junit5:4.6.0")
    testImplementation("io.kotest:kotest-assertions-core:4.6.0")
    testImplementation("io.kotest:kotest-property:4.6.0")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.7.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.7.2")
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "11"
}

tasks.withType<Test> {
    useJUnitPlatform()
}

application {
    mainClassName = "MainKt"
}