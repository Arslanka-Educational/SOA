import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.2.5"
    id("io.spring.dependency-management") version "1.1.4"
    kotlin("jvm") version "1.9.22"
    kotlin("plugin.spring") version "1.9.22"
    kotlin("plugin.jpa") version "1.9.22"
}

group = "com.ifmo.se.route.management"
version = "1"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
}

configurations {
    create("jaxb")
}

dependencies {
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
    implementation("org.springframework.boot:spring-boot-starter-tomcat")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-web-services")
    "jaxb"("org.glassfish.jaxb:jaxb-xjc")
    implementation("wsdl4j:wsdl4j")
}
tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}

sourceSets {
    named("main") {
        java {
            srcDir("src/main/kotlin")
            srcDir("build/generated-sources/jaxb")
        }
    }
}

tasks.register("generateJaxb") {
    val sourcesDir = "${buildDir.path}/generated-sources/jaxb"
    val schema = "src/main/resources/wsdl/schema.xsd"

    outputs.dir(sourcesDir)

    doLast {
        ant.withGroovyBuilder {
            "taskdef"(
                "name" to "xjc",
                "classname" to "com.sun.tools.xjc.XJCTask",
                "classpath" to configurations["jaxb"].asPath
            )
            "mkdir"("dir" to sourcesDir)

            "xjc"(
                "destdir" to sourcesDir,
                "schema" to schema
            ) {
                "arg"("value" to "-wsdl")
                "produces"("dir" to sourcesDir, "includes" to "**/*.java")
            }
        }
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.named("compileKotlin") {
    dependsOn("generateJaxb")
}

tasks.named("compileJava") {
    dependsOn("generateJaxb")
}