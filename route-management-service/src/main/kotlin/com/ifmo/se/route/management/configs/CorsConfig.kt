package org.example.com.ifmo.se.route.management.configs

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.invoke
import org.springframework.security.web.SecurityFilterChain
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
@EnableWebSecurity
open class CorsConfig : WebMvcConfigurer {
    @Bean
    open fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http.invoke {
            authorizeRequests {
                authorize(anyRequest, permitAll)
            }
            cors {  }
            csrf { disable() }
        }

        return http.build()
    }

    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**")
            .allowedOriginPatterns("http://localhost:*")
            .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Allow all HTTP methods
            .allowedHeaders("*")
            .allowCredentials(false) // Allow credentials (if needed)
    }
}