plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}
rootProject.name = "navigator-ejb"
include(":navigator-models")
project(":navigator-models").projectDir = File("../navigator-models")

