plugins {
    kotlin("jvm") version "1.9.23"
    id("org.springframework.boot") version "3.2.0"
    id("io.spring.dependency-management") version "1.1.3"
    id("org.openapi.generator") version "7.0.0"
    id("war")
}

group = "ru.openbook"
version = "1.0-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
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
    testImplementation("org.springframework.boot:spring-boot-starter-test")
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

val serverName = "navigator-service-api"
val serverOpenApiSpec = "src/main/resources/server/$serverName/navigator-service-api.yaml"

tasks.register("generateServer") {
    doLast {
        exec {
            commandLine(
                "openapi-generator-cli", "generate", "-i", serverOpenApiSpec,
                "-g", "kotlin-spring", "-o", "build/generated-server/navigator-service-api", "--additional-properties=interfaceOnly=true",
                "--config", "src/main/resources/server/navigator-service-api/api-config.json",
                "--skip-validate-spec",
                "--global-property=apis,models,supportingFiles,useTags"
            )
        }
    }
}

val openApiSpecsDir = projectDir.resolve("src/main/resources/clients")
val generatedCodeDir = projectDir.resolve("build/generated-clients")

tasks.register("generateClients") {
    doLast {
        openApiSpecsDir.walkTopDown()
            .filter { file -> file.isFile && (file.extension == "yaml") }
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
                        "--config", "src/main/resources/clients/${specFile.parentFile.nameWithoutExtension}/api-config.json",
                        "--skip-validate-spec",
                        "--global-property=apis,models,supportingFiles,useTags"
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