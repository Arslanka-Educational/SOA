package org.example.com.ifmo.se.route.management.configs

import org.springframework.boot.web.servlet.ServletRegistrationBean
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource
import org.springframework.ws.config.annotation.EnableWs
import org.springframework.ws.config.annotation.WsConfigurerAdapter
import org.springframework.ws.transport.http.MessageDispatcherServlet
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition
import org.springframework.xml.xsd.SimpleXsdSchema
import org.springframework.xml.xsd.XsdSchema

@Configuration
@EnableWs
class EndpointConfig : WsConfigurerAdapter() {

    @Bean
    fun messageDispatcherServlet(
        applicationContext: ApplicationContext,
    ): ServletRegistrationBean<MessageDispatcherServlet> {
        val servlet = MessageDispatcherServlet()
        servlet.setApplicationContext(applicationContext)
        servlet.isTransformWsdlLocations = true
        return ServletRegistrationBean<MessageDispatcherServlet>(servlet, "/ws/*")
    }

    @Bean(name = ["locations"])
    fun locationsWsdl(
        xsdSchema: XsdSchema
    ): DefaultWsdl11Definition {
        val wsdl11Definition = DefaultWsdl11Definition()
        wsdl11Definition.setPortTypeName("LocationsPort")
        wsdl11Definition.setLocationUri("/ws/locations")
        wsdl11Definition.setTargetNamespace("http://ifmo.com/se/route-management")
        wsdl11Definition.setSchema(xsdSchema)
        return wsdl11Definition
    }

    @Bean(name = ["routes"])
    fun routesWsdl(
        xsdSchema: XsdSchema
    ): DefaultWsdl11Definition {
        val wsdl11Definition = DefaultWsdl11Definition()
        wsdl11Definition.setPortTypeName("RoutesPort")
        wsdl11Definition.setLocationUri("/ws/routes")
        wsdl11Definition.setTargetNamespace("http://ifmo.com/se/route-management")
        wsdl11Definition.setSchema(xsdSchema)
        return wsdl11Definition
    }

    @Bean
    fun xsdSchema(): XsdSchema {
        return SimpleXsdSchema(ClassPathResource("wsdl/schema.xsd"))
    }
}