package org.example.com.ifmo.se.route.management.configs

import com.fasterxml.jackson.databind.ObjectMapper
import org.example.com.ifmo.se.route.management.data.models.converter.GetRoutesFilterParameterDtoConverter
import org.example.com.ifmo.se.route.management.data.models.converter.StringToSortFieldsDtoConverter
import org.springframework.context.annotation.Configuration
import org.springframework.format.FormatterRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
open class WebConfig(
    private val objectMapper: ObjectMapper,
) : WebMvcConfigurer {
    override fun addFormatters(registry: FormatterRegistry) {
        registry.addConverter(StringToSortFieldsDtoConverter())
        registry.addConverter(GetRoutesFilterParameterDtoConverter(objectMapper))
    }
}