package org.example

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan(
    basePackages = [
        "org.example",
        "org.example.com.ifmo.se.route.management.controllers",
        "org.example.com.ifmo.se.route.management.services",
        "org.example.com.ifmo.se.route.management.data",
    ]
)
class Application

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}