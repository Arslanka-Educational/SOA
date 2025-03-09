package com.ifmo.se.navigator.dtos

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.Valid
import io.swagger.v3.oas.annotations.media.Schema

/**
 * 
 * @param locations 
 * @param total Общее количество элементов
 */
data class LocationResponseDto(

    @field:Valid
    @Schema(example = "null", description = "")
    @get:JsonProperty("locations") val locations: List<LocationDto>? = null,

    @Schema(example = "null", description = "Общее количество элементов")
    @get:JsonProperty("total") val total: Int? = null
    ) {

}

