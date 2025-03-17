package com.ifmo.se.navigator.com.ifmo.se.navigator.configs

import com.ifmo.se.navigator.ejb.services.LocationManagementService
import com.ifmo.se.navigator.ejb.services.NavigatorService
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory
import org.springframework.boot.web.servlet.server.ServletWebServerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.Properties
import javax.naming.Context
import javax.naming.InitialContext
import org.apache.catalina.connector.Connector


@Configuration
open class ApplicationConfig {
    @Bean
    open fun servletContainer(@Value("\${server.http.port}") httpPort: Int): ServletWebServerFactory {
        val connector = Connector(TomcatServletWebServerFactory.DEFAULT_PROTOCOL)
        connector.port = httpPort

        val tomcat = TomcatServletWebServerFactory()
        tomcat.addAdditionalTomcatConnectors(connector)
        return tomcat
    }

    @Bean
    open fun context(): Context {
        val jndiProps = Properties()
        jndiProps[Context.INITIAL_CONTEXT_FACTORY] = "org.wildfly.naming.client.WildFlyInitialContextFactory"
        jndiProps["jboss.naming.client.ejb.context"] = true
        jndiProps["remote.connectionprovider.create.options.org.xnio.Options.SSL_ENABLED"] = false
        jndiProps["remote.connection.default.connect.options.org.xnio.Options.SASL_POLICY_NOANONYMOUS"] = false
        jndiProps["remote.connection.default.connect.options.org.xnio.Options.SASL_POLICY_NOPLAINTEXT"] = false
        jndiProps[Context.SECURITY_PRINCIPAL] = "user"
        jndiProps[Context.SECURITY_CREDENTIALS] = "user"
        jndiProps[Context.PROVIDER_URL] = "http-remoting://navigator-ejb:8080"

        return InitialContext(jndiProps)
    }

    @Bean
    open fun locationManagementService(context: Context): LocationManagementService =
        context.lookup("ejb:/navigator-ejb-1.0-SNAPSHOT/LocationManagementServiceImpl!com.ifmo.se.navigator.ejb.services.LocationManagementService")
                as LocationManagementService

    @Bean
    open fun navigatorService(context: Context): NavigatorService =
        context.lookup("ejb:/navigator-ejb-1.0-SNAPSHOT/NavigatorServiceImpl!com.ifmo.se.navigator.ejb.services.NavigatorService")
                as NavigatorService
}