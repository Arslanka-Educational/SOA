plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}
rootProject.name = "navigator-ejb"
include(":navigator-models")
project(":navigator-models").projectDir = File("../navigator-models")

