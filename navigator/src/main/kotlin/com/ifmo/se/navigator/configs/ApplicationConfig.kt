package com.ifmo.se.navigator.com.ifmo.se.navigator.configs

import com.ifmo.se.navigator.ejb.services.LocationManagementService
import com.ifmo.se.navigator.ejb.services.LocationManagementServiceImpl
import com.ifmo.se.navigator.ejb.services.NavigatorService
import com.ifmo.se.navigator.ejb.services.NavigatorServiceImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.naming.InitialContext

@Configuration
open class ApplicationConfig {
    private val context = InitialContext()

    @Bean
    open fun locationManagementService() = getRemoteBean(LocationManagementService::class.java)

    @Bean
    open fun navigatorService() = getRemoteBean(NavigatorService::class.java)

    /**
     * @param clazz remote интерфейс EJB бина
     * @return Реализация бина с постфиксом Impl
     */
    @Suppress("UNCHECKED_CAST")
    open fun <T> getRemoteBean(clazz: Class<T>): T {
        val moduleName = "navigator-ejb-1.0-SNAPSHOT"
        val beanName = clazz.simpleName
        val viewClassName = clazz.name
        val toLookup = "ejb:/${moduleName}/${beanName}Impl!${viewClassName}"
        return context.lookup(toLookup) as T
    }

}