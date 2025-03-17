plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}
rootProject.name = "navigator"
include(":navigator-models")
project(":navigator-models").projectDir = File("../navigator-models")
include(":navigator-ejb")
project(":navigator-ejb").projectDir = File("../navigator-ejb")

