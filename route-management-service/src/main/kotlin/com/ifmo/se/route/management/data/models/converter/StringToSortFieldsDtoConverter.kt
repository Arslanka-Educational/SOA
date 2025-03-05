package org.example.com.ifmo.se.route.management.data.models.converter

import generated.com.ifmo.se.route.dto.SortFieldsDto
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component

@Component
open class StringToSortFieldsDtoConverter : Converter<String, SortFieldsDto> {
    override fun convert(source: String): SortFieldsDto {
        return SortFieldsDto.forValue(source)
    }
}