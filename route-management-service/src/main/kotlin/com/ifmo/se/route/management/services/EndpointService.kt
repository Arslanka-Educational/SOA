package org.example.com.ifmo.se.route.management.services

import jakarta.annotation.PostConstruct
import org.springframework.stereotype.Service
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping

@Service
class EndpointService(private val requestMappingHandlerMapping: RequestMappingHandlerMapping) {

    @PostConstruct
    fun logEndpoints() {
        requestMappingHandlerMapping.handlerMethods.forEach { (info, handlerMethod) ->
            val patterns = info.patternsCondition?.patterns?.joinToString(", ") ?: ""
            val methods = info.methodsCondition?.methods?.joinToString(", ") { it.name } ?: ""
            val handler = handlerMethod.toString()
            println("Path: $patterns ($methods) -> Handler: $handler")
        }
    }
}