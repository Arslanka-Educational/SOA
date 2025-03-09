package com.ifmo.se.navigator.dtos

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.Valid
import jakarta.validation.constraints.DecimalMin
import jakarta.validation.constraints.Size

/**
 * 
 * @param name 
 * @param coordinates 
 * @param distance Дистанция до маршрута
 * @param from 
 * @param to 
 */
data class RouteUpsertRequestDto(

    @get:Size(min=1)
    @Schema(example = "null", required = true, description = "")
    @get:JsonProperty("name", required = true) val name: kotlin.String,

    @field:Valid
    @Schema(example = "null", required = true, description = "")
    @get:JsonProperty("coordinates", required = true) val coordinates: CoordinatesDto?,

    @get:DecimalMin("1")
    @Schema(example = "null", required = true, description = "Дистанция до маршрута")
    @get:JsonProperty("distance", required = true) val distance: kotlin.Double,

    @field:Valid
    @Schema(example = "null", description = "")
    @get:JsonProperty("from") val from: LocationDto? = null,

    @field:Valid
    @Schema(example = "null", description = "")
    @get:JsonProperty("to") val to: LocationDto? = null
    ) {

}

