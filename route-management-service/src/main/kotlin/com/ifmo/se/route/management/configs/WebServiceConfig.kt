package org.example.com.ifmo.se.route.management.configs

import org.apache.cxf.Bus
import org.apache.cxf.bus.spring.SpringBus
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@Configuration
@Import(value = [EndpointConfig::class])
open class WebServiceConfig {
    @Suppress("unused")
    @Bean(name = [Bus.DEFAULT_BUS_ID])
    open fun springBus(): SpringBus {
        return SpringBus()
    }
}