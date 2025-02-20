package com.ifmo.se.navigator.com.ifmo.se.navigator.configs

import com.ifmo.se.navigator.com.ifmo.se.navigator.clients.RouteManagementClient
import feign.Feign
import feign.Request
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.concurrent.TimeUnit

@Configuration
open class FeignClientConfig {

    @Value("\${client.route-management.server.url}")
    private lateinit var url: String

    @Value("\${client.route-management.server.connect-timeout}")
    private var connectTimeout: Long = 5000

    @Value("\${client.route-management.server.read-timeout}")
    private var readTimeout: Long = 10000

    @Bean
    open fun feignRequestOptions(): Request.Options {
        return Request.Options(connectTimeout, TimeUnit.MILLISECONDS, readTimeout, TimeUnit.MILLISECONDS, false)
    }

    @Bean
    open fun feignClient(): RouteManagementClient {
        return Feign.builder()
            .options(feignRequestOptions())
            .target(RouteManagementClient::class.java, url)
    }
}