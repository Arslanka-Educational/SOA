import org.gradle.kotlin.dsl.implementation

plugins {
    kotlin("jvm") version "1.9.23"
    id("java")
}

group = "com.ifmo.se.navigator.ejb"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("jakarta.ws.rs:jakarta.ws.rs-api:3.0.0")
    implementation("jakarta.ejb:jakarta.ejb-api:4.0.1")
    implementation("jakarta.annotation:jakarta.annotation-api:2.0.0")
    implementation("jakarta.enterprise:jakarta.enterprise.cdi-api:3.0.0")
    implementation("jakarta.platform:jakarta.jakartaee-api:10.0.0")
    implementation("org.jboss.ejb3:jboss-ejb3-ext-api:2.4.0.Final")
    implementation("org.apache.httpcomponents.client5:httpclient5:5.3.1")
    implementation(project(":navigator-models"))
}

kotlin {
    jvmToolchain(17)
}

tasks.withType<Jar> {
    manifest {

    }

    duplicatesStrategy = DuplicatesStrategy.EXCLUDE

    from(sourceSets.main.get().output)

    dependsOn(configurations.runtimeClasspath)
    from({
        configurations.runtimeClasspath.get().filter { it.name.endsWith("jar") }.map { zipTree(it) }
    })
}

tasks.test {
    useJUnitPlatform()
}