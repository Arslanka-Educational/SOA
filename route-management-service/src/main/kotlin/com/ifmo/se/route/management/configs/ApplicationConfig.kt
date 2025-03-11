package org.example.com.ifmo.se.route.management.configs

import org.apache.catalina.connector.Connector
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory
import org.springframework.boot.web.servlet.server.ServletWebServerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate

@Configuration
open class ApplicationConfig {
    @Bean
    open fun restTemplate(builder: RestTemplateBuilder): RestTemplate {
        return builder.build()
    }

    @Bean
    open fun servletContainer(@Value("\${server.http.port}") httpPort: Int): ServletWebServerFactory {
        val connector = Connector(TomcatServletWebServerFactory.DEFAULT_PROTOCOL)
        connector.port = httpPort

        val tomcat = TomcatServletWebServerFactory()
        tomcat.addAdditionalTomcatConnectors(connector)
        return tomcat
    }
}