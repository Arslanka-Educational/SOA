package com.ifmo.se.navigator.com.ifmo.se.navigator.configs

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jndi.JndiObjectFactoryBean

@Configuration
open class ApplicationConfig {
    @Bean
    open fun locationManagementServiceInterface(): JndiObjectFactoryBean {
        val factoryBean = JndiObjectFactoryBean()
        factoryBean.jndiName = "ejb:/navigator-ejb-1.0-SNAPSHOT/LocationManagementService!com.ifmo.se.navigator.ejb.services.LocationManagementServiceInterface"
        factoryBean.isResourceRef = true
        return factoryBean
    }

    @Bean
    open fun navigatorServiceInterface(): JndiObjectFactoryBean {
        val factoryBean = JndiObjectFactoryBean()
        factoryBean.jndiName = "ejb:/navigator-ejb-1.0-SNAPSHOT/NavigatorService!com.ifmo.se.navigator.ejb.services.NavigatorServiceInterface"
        factoryBean.isResourceRef = true
        return factoryBean
    }

}