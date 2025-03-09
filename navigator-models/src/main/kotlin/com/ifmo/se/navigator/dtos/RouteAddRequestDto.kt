package com.ifmo.se.navigator.dtos

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.Valid
import io.swagger.v3.oas.annotations.media.Schema

/**
 * 
 * @param name 
 * @param coordinates 
 */
data class RouteAddRequestDto(

    @Schema(example = "null", required = true, description = "")
    @get:JsonProperty("name", required = true) val name: kotlin.String,

    @field:Valid
    @Schema(example = "null", description = "")
    @get:JsonProperty("coordinates") val coordinates: CoordinatesDto? = null
    ) {

}

