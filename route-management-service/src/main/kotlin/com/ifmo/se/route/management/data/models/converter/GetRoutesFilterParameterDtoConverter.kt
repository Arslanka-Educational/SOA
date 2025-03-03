package org.example.com.ifmo.se.route.management.data.models.converter

import com.fasterxml.jackson.databind.ObjectMapper
import generated.com.ifmo.se.route.dto.GetRoutesFilterParameterDto
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component

@Component
open class GetRoutesFilterParameterDtoConverter(
    private val objectMapper: ObjectMapper
) : Converter<String, GetRoutesFilterParameterDto> {

    override fun convert(source: String): GetRoutesFilterParameterDto {
        return try {
            objectMapper.readValue(source, GetRoutesFilterParameterDto::class.java)
        } catch (e: Exception) {
            throw IllegalArgumentException("Ошибка при преобразовании filter: ${e.message}", e)
        }
    }
}