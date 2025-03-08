import org.gradle.kotlin.dsl.implementation

plugins {
    kotlin("jvm") version "1.9.23"
    id("java")
    id("war")
}

group = "com.ifmo.se.navigator.ejb"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("jakarta.enterprise:jakarta.enterprise.cdi-api:3.0.0")
    implementation("jakarta.ejb:jakarta.ejb-api:4.0.0")
    implementation("jakarta.ws.rs:jakarta.ws.rs-api:3.0.0")
    implementation("org.jboss.ejb3:jboss-ejb3-ext-api:2.4.0.Final")
    implementation("jakarta.ejb:jakarta.ejb-api:4.0.1")
    implementation("jakarta.annotation:jakarta.annotation-api:2.0.0")
    implementation("jakarta.enterprise:jakarta.enterprise.cdi-api:3.0.0")
    implementation("org.jboss.ejb3:jboss-ejb3-ext-api:2.4.0.Final")
    implementation("org.apache.httpcomponents.client5:httpclient5:5.3.1")
    implementation("org.openapitools:openapi-generator:7.0.0")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("io.swagger.core.v3:swagger-annotations:2.2.8")
    implementation("io.swagger.core.v3:swagger-models:2.2.8")
    implementation("org.jboss.resteasy:resteasy-client:4.7.3.Final")
    implementation("io.github.microutils:kotlin-logging-jvm:3.0.5")
    implementation("org.apache.httpcomponents:httpclient:4.5.14")
    implementation(project(":navigator-models"))
}

kotlin {
    jvmToolchain(17)
}

tasks.withType<War> {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE

    configurations["compileClasspath"].forEach { file: File ->
        from(zipTree(file.absoluteFile))
    }
}

tasks.test {
    useJUnitPlatform()
}

val routeManagementClientModels = "route-management-service-api"
val routeManagementClientModelsSpec = "../clients/$routeManagementClientModels/client-models.yaml"

tasks.register("generateClients") {
    doLast {
        val specs = mapOf(
            routeManagementClientModels to routeManagementClientModelsSpec
        )

        specs.forEach { (clientName, specPath) ->
            exec {
                commandLine(
                    "openapi-generator-cli", "generate", "-i", specPath,
                    "-g",
                    "kotlin-spring",
                    "-o", "build/generated-client/$clientName",
                    "--additional-properties=interfaceOnly=true",
                    "--config", "../clients/$clientName/server/api-config.json",
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
            srcDir("build/generated-client")
        }
    }
}

tasks.named("compileKotlin") {
    dependsOn("generateClients")
}

tasks.named("compileJava") {
    dependsOn("generateClients")
}