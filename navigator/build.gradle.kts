import com.mkring.wildlydeplyplugin.DeployWildflyTask

plugins {
    kotlin("jvm") version "1.9.23"
    id("org.springframework.boot") version "3.2.0"
    id("io.spring.dependency-management") version "1.1.3"
    id("org.openapi.generator") version "7.0.0"
    id("war")
    id("com.mkring.wildlydeplyplugin.deploy-wildfly-plugin") version "0.3.0"
}

group = "com.ifmo.se.navigator"
version = "1.0-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_21

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web") {
        exclude(group = "org.springframework.boot", module = "spring-boot-starter-tomcat")
    }
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("io.ktor:ktor-client-core:2.0.0")
    implementation("io.ktor:ktor-client-cio:2.0.0")
    implementation("io.ktor:ktor-client-content-negotiation:2.0.0")
    implementation("io.ktor:ktor-serialization-kotlinx-json:2.0.0")
    implementation("io.kotlintest:kotlintest:2.0.7")
    implementation("io.kotlintest:kotlintest-runner-junit5:3.1.9")
    implementation("com.squareup.moshi:moshi:1.14.0")
    implementation("com.squareup.moshi:moshi-kotlin:1.14.0")
    implementation("io.swagger.core.v3:swagger-annotations:2.2.8")
    implementation("io.swagger.core.v3:swagger-models:2.2.8")
    implementation("org.hibernate.validator:hibernate-validator:8.0.0.Final")
    implementation("javax.validation:validation-api:2.0.1.Final")
    implementation("javax.servlet:javax.servlet-api:4.0.1")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("io.github.microutils:kotlin-logging-jvm:3.0.5")
    implementation("org.springframework.boot:spring-boot-configuration-processor")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

configurations {
    all {
        exclude(group = "org.springframework.boot", module = "spring-boot-starter-logging")
        exclude(group = "ch.qos.logback", module = "logback-classic")
        exclude(group = "org.apache.logging.log4j", module = "log4j-to-slf4j")
    }
}
tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "21"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

val serverName = "navigator-service-api"
val serverOpenApiSpec = "../clients/$serverName/navigator-service-api.yaml"

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
                "--config",
                "../clients/$serverName/server/api-config.json",
                "--skip-validate-spec",
                "--global-property=apis,models,useTags"
            )
        }
    }
}

val openApiSpecsDir = projectDir.resolve("../clients")
val generatedCodeDir = projectDir.resolve("build/generated-clients")

tasks.register("generateClients") {
    doLast {
        openApiSpecsDir.walkTopDown().filter { file ->
            file.isFile && (file.extension == "yaml") && File("../clients/${file.parentFile.nameWithoutExtension}/client/api-config.json").exists()
        }
            .forEach { specFile ->
                val clientName = specFile.nameWithoutExtension
                val outputDir = generatedCodeDir.resolve(clientName)

                println("Generating client for $clientName from ${specFile.name}")

                exec {
                    commandLine(
                        "openapi-generator-cli", "generate",
                        "-i", specFile.absolutePath,
                        "-g", "kotlin",
                        "-o", outputDir.absolutePath,
                        "--config", "../clients/${specFile.parentFile.nameWithoutExtension}/client/api-config.json",
                        "--skip-validate-spec",
                        "--global-property=apis,models,useTags"
                    )
                }
            }
    }
}

sourceSets {
    main {
        kotlin {
            srcDir("build/generated-clients")
            srcDir("build/generated-server")
        }
    }
}

tasks.named("compileKotlin") {
    dependsOn("generateServer")
    dependsOn("generateClients")
}

tasks.named("compileJava") {
    dependsOn("generateServer")
    dependsOn("generateClients")
}