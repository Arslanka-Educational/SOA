package org.example.com.ifmo.se.route.management.configs

import jakarta.xml.ws.Endpoint;
import org.apache.cxf.Bus;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.EndpointImpl;
import org.example.com.ifmo.se.route.management.controllers.LocationsController
import org.example.com.ifmo.se.route.management.controllers.RoutesController
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;

@Configuration
open class EndpointConfig @Autowired constructor(private val bus: Bus) : WsConfigurerAdapter() {
    @Bean
    open fun locationsEndpoint(
        bus: Bus,
        locationsController: LocationsController
    ): Endpoint {
        return createSoapEndpoint(bus, locationsController, "/locations")
    }

    @Bean
    open fun routesEndpoint(
        bus: Bus,
        routesController: RoutesController,
    ): Endpoint {
        return createSoapEndpoint(bus, routesController, "/routes")
    }

    private fun createSoapEndpoint(
        bus: Bus,
        controller: Any,
        path: String
    ): Endpoint {
        val endpoint = EndpointImpl(bus, controller).apply {
            publish(path)
            server.endpoint.inInterceptors.add(LoggingInInterceptor())
            server.endpoint.outInterceptors.add(LoggingOutInterceptor())
        }
        return endpoint
    }
}