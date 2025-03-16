package org.example.com.ifmo.se.route.management.configs

import jakarta.xml.ws.Endpoint
import org.apache.cxf.Bus
import org.apache.cxf.interceptor.LoggingInInterceptor
import org.apache.cxf.interceptor.LoggingOutInterceptor
import org.apache.cxf.jaxws.EndpointImpl
import org.example.com.ifmo.se.route.management.controllers.LocationsController
import org.example.com.ifmo.se.route.management.controllers.RoutesController
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.webservices.WebServicesAutoConfiguration
import org.springframework.boot.web.servlet.ServletRegistrationBean
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource
import org.springframework.ws.config.annotation.EnableWs
import org.springframework.ws.config.annotation.WsConfigurerAdapter
import org.springframework.ws.server.endpoint.mapping.PayloadRootAnnotationMethodEndpointMapping
import org.springframework.ws.transport.http.MessageDispatcherServlet
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition
import org.springframework.xml.xsd.SimpleXsdSchema

@Configuration
@EnableAutoConfiguration(exclude = [WebServicesAutoConfiguration::class])
@EnableWs
open class EndpointConfig : WsConfigurerAdapter() {

    @Bean("customMessageDispatcherServlet")
    open fun messageDispatcherServlet(applicationContext: ApplicationContext): ServletRegistrationBean<MessageDispatcherServlet?> {
        val servlet = MessageDispatcherServlet()
        servlet.setApplicationContext(applicationContext)
        servlet.isTransformWsdlLocations = true
        return ServletRegistrationBean<MessageDispatcherServlet?>(servlet, "/ws/*")
    }

    @Bean(name = ["locations"])
    open fun locationsWsdl(): DefaultWsdl11Definition {
        val wsdl11Definition = DefaultWsdl11Definition()
        wsdl11Definition.setPortTypeName("LocationsPort")
        wsdl11Definition.setLocationUri("/ws/locations")
        wsdl11Definition.setTargetNamespace("http://ifmo.com/se/route-management")
        wsdl11Definition.setSchema(xsdSchema())
        return wsdl11Definition
    }

    @Bean(name = ["routes"])
    open fun routesWsdl(): DefaultWsdl11Definition {
        val wsdl11Definition = DefaultWsdl11Definition()
        wsdl11Definition.setPortTypeName("RoutesPort")
        wsdl11Definition.setLocationUri("/ws/routes")
        wsdl11Definition.setTargetNamespace("http://ifmo.com/se/route-management")
        wsdl11Definition.setSchema(xsdSchema())
        return wsdl11Definition
    }

    @Bean
    open fun xsdSchema(): SimpleXsdSchema {
        return SimpleXsdSchema(ClassPathResource("wsdl/schema.xsd"))
    }

    @Bean
    open fun endpointMapping(): PayloadRootAnnotationMethodEndpointMapping {
        return PayloadRootAnnotationMethodEndpointMapping()
    }
}