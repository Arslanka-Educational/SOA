package com.ifmo.se.navigator.configs

import generated.com.ifmo.se.route.api.LocationsApi
import generated.com.ifmo.se.route.api.RoutesApi
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ApiConfig(
    @Value("\${client.route-management.server.url}")
    private val routeManagementUrl: String,
) {

    @Bean
    fun locationsApi() = LocationsApi(
        baseUrl = routeManagementUrl,
    )

    @Bean
    fun routesApi() = RoutesApi(
        baseUrl = routeManagementUrl,
    )
}