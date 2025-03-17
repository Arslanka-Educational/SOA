//package org.example.com.ifmo.se.route.management.configs
//
//import org.springframework.context.annotation.Bean
//import org.springframework.context.annotation.Configuration
//import org.springframework.security.config.annotation.web.builders.HttpSecurity
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
//import org.springframework.security.config.annotation.web.invoke
//import org.springframework.security.web.SecurityFilterChain
//import org.springframework.web.servlet.config.annotation.CorsRegistry
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
//
//@Configuration
//@EnableWebSecurity
//class CorsConfig : WebMvcConfigurer {
//    @Bean
//    fun filterChain(http: HttpSecurity): SecurityFilterChain {
//        http {
//            authorizeRequests {
//                authorize("/ws/**", permitAll)
//                authorize("/actuator/**", permitAll)
//                authorize(anyRequest, authenticated)
//            }
//
//            csrf { disable() }
//
//            httpBasic { }
//        }
//
//        return http.build()
//    }
//
//    override fun addCorsMappings(registry: CorsRegistry) {
//        registry.addMapping("/ws/**")
//            .allowedOrigins("*")
//            .allowedMethods("POST", "GET")
//            .allowedHeaders("*")
//            .allowCredentials(false) // Или true, если нужно
//    }
//}