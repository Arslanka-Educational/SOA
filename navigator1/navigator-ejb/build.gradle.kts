import org.gradle.kotlin.dsl.implementation

plugins {
    kotlin("jvm") version "2.0.0"
}

group = "com.ifmo.se.navigator.ejb"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

tasks.withType<Jar> {
    // Otherwise you'll get a "No main manifest attribute" error
    manifest {

    }

    // To avoid the duplicate handling strategy error
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE

    // To add all of the dependencies
    from(sourceSets.main.get().output)

    dependsOn(configurations.runtimeClasspath)
    from({
        configurations.runtimeClasspath.get().filter { it.name.endsWith("jar") }.map { zipTree(it) }
    })
}

dependencies {
    implementation("org.postgresql:postgresql:42.7.3")
    implementation("jakarta.ejb:jakarta.ejb-api:4.0.1")
    implementation("jakarta.annotation:jakarta.annotation-api:2.0.0")
    implementation("jakarta.enterprise:jakarta.enterprise.cdi-api:3.0.0")
    implementation("org.jboss.ejb3:jboss-ejb3-ext-api:2.4.0.Final")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(17)
}

