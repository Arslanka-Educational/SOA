package org.example

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer

@SpringBootApplication
@EntityScan(basePackages = ["org.example.com.ifmo.se.route.management.data.models"])
open class Application : SpringBootServletInitializer()

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}