plugins {
    kotlin("jvm") version "1.9.23"
    id("org.springframework.boot") version "3.2.0"
    id("io.spring.dependency-management") version "1.1.3"
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
    implementation("org.springframework.ws:spring-ws-core:4.0.11")
    implementation("org.apache.cxf:cxf-spring-boot-starter-jaxws:4.0.5")
    implementation("org.springframework.boot:spring-boot-starter-web-services")
    implementation("javax.xml.bind:jaxb-api:2.3.1")
    implementation("org.glassfish.jaxb:jaxb-runtime:4.0.4")
    implementation("javax.activation:activation:1.1.1")
    runtimeOnly("com.sun.xml.bind:jaxb-core")
    runtimeOnly("com.sun.xml.bind:jaxb-impl")
    runtimeOnly("com.sun.xml.messaging.saaj:saaj-impl")
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

tasks.withType<Jar> {
    duplicatesStrategy = DuplicatesStrategy.INCLUDE
}

tasks.register("generateJaxb") {
    val generatedSources = file("build/generated-sources")
    outputs.dir(generatedSources)

    doLast {
        generatedSources.mkdirs()
        exec {
            commandLine(
                "xjc",
                "-d", generatedSources.path,
                "-p", "com.ifmo.se.route.management.wsdl",
                "src/main/resources/wsdl/schema.xsd"
            )
        }
    }
}

sourceSets {
    main {
        java {
            srcDir("build/generated-sources")
        }
    }
}

tasks.named("compileKotlin") {
    dependsOn("generateJaxb")
}

tasks.named("compileJava") {
    dependsOn("generateJaxb")
}