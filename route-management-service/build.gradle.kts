plugins {
    kotlin("jvm") version "1.9.23"
    id("org.springframework.boot") version "3.2.0"
    id("io.spring.dependency-management") version "1.1.3"
    id("org.openapi.generator") version "7.0.0"
    kotlin("plugin.jpa") version "1.9.22"
}

group = "com.ifmo.se.route.management"
version = "1"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("io.swagger.core.v3:swagger-annotations:2.2.8")
    implementation("io.swagger.core.v3:swagger-models:2.2.8")
    implementation("org.hibernate.validator:hibernate-validator:8.0.0.Final")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("io.github.microutils:kotlin-logging-jvm:3.0.5")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    compileOnly("org.projectlombok:lombok:1.18.36")
    annotationProcessor("org.projectlombok:lombok:1.18.36")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.postgresql:postgresql:42.6.0")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.0.1")
    implementation("org.springframework.boot:spring-boot-starter-tomcat")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.apache.logging.log4j:log4j-slf4j-impl:2.22.0")
    implementation("org.apache.logging.log4j:log4j-core:2.22.0")
    implementation("org.apache.logging.log4j:log4j-api:2.22.0")

}
tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

val serverName = "route-management-service-api"
val serverOpenApiSpec = "../clients/$serverName/route-management-service.yaml"

tasks.register("generateServer") {
    doLast {
        exec {
            commandLine(
                "openapi-generator-cli", "generate", "-i", serverOpenApiSpec,
                "-g",
                "kotlin-spring",
                "-o",
                "build/generated-server/$serverName",
                "--additional-properties=interfaceOnly=true",
                "--config", "../clients/$serverName/server/api-config.json",
                "--skip-validate-spec",
                "--global-property=apis,models,supportingFiles,useTags"
            )
        }
    }
}

sourceSets {
    main {
        kotlin {
            srcDir("build/generated-server")
        }
    }
}

tasks.named("compileKotlin") {
    dependsOn("generateServer")
}

tasks.named("compileJava") {
    dependsOn("generateServer")
}