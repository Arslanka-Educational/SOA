plugins {
    kotlin("jvm") version "1.9.23"
    id("org.springframework.boot") version "3.2.0"
    id("io.spring.dependency-management") version "1.1.3"
    id("org.openapi.generator") version "7.0.0"
    id("war")
    id("com.mkring.wildlydeplyplugin.deploy-wildfly-plugin") version "0.3.0"
}

group = "com.ifmo.se.navigator"
version = "1"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web") {
        exclude(group = "org.springframework.boot", module = "spring-boot-starter-tomcat")
    }
    implementation("org.openapitools:openapi-generator:7.0.0")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("io.swagger.core.v3:swagger-annotations:2.2.8")
    implementation("io.swagger.core.v3:swagger-models:2.2.8")
    implementation("org.hibernate.validator:hibernate-validator:8.0.0.Final")
    implementation("javax.validation:validation-api:2.0.1.Final")
    implementation("javax.servlet:javax.servlet-api:4.0.1")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("io.github.microutils:kotlin-logging-jvm:3.0.5")
    implementation("org.springframework.boot:spring-boot-configuration-processor")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    implementation("org.apache.httpcomponents.client5:httpclient5:5.2.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.1")
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
val serverOpenApiSpec = "../clients/$serverName/navigator-service-api.yaml"
val routeManagementClientModels = "route-management-service-api"
val routeManagementClientModelsSpec = "../clients/$routeManagementClientModels/client-models.yaml"

tasks.register("generateServer") {
    doLast {
        val specs = mapOf(
            serverName to serverOpenApiSpec,
            routeManagementClientModels to routeManagementClientModelsSpec
        )

        specs.forEach { (serverName, specPath) ->
            exec {
                commandLine(
                    "openapi-generator-cli", "generate", "-i", specPath,
                    "-g",
                    "kotlin-spring",
                    "-o", "build/generated-server/$serverName",
                    "--additional-properties=interfaceOnly=true",
                    "--config", "../clients/$serverName/server/api-config.json",
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