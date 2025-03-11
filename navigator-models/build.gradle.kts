plugins {
    kotlin("jvm") version "1.9.23"
}

group = "com.ifmo.se.navigator.models"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("jakarta.ejb:jakarta.ejb-api:4.0.1")
    implementation("jakarta.json.bind:jakarta.json.bind-api:3.0.0")
    implementation("jakarta.annotation:jakarta.annotation-api:2.0.0")
    implementation("jakarta.enterprise:jakarta.enterprise.cdi-api:3.0.0")
    implementation("jakarta.validation:jakarta.validation-api:3.0.2")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("io.swagger.core.v3:swagger-annotations:2.2.8")
    implementation("io.swagger.core.v3:swagger-models:2.2.8")
}


tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}