package com.ifmo.se.navigator.dtos

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.Valid
import jakarta.validation.constraints.DecimalMin
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.Size

/**
 * 
 * @param id Уникальный идентификатор маршрута1
 * @param name Название маршрута
 * @param coordinates 
 * @param creationDate 
 * @param from 
 * @param to 
 * @param distance Дистанция до маршрута
 */
data class RouteDto(

    @get:Min(1L)
    @Schema(example = "null", required = true, readOnly = true, description = "Уникальный идентификатор маршрута1")
    @get:JsonProperty("id", required = true) val id: kotlin.Long,

    @get:Size(min=1)
    @Schema(example = "null", required = true, description = "Название маршрута")
    @get:JsonProperty("name", required = true) val name: kotlin.String,

    @field:Valid
    @Schema(example = "null", required = true, description = "")
    @get:JsonProperty("coordinates", required = true) val coordinates: CoordinatesDto?,

    @Schema(example = "null", required = true, readOnly = true, description = "")
    @get:JsonProperty("creationDate", required = true) val creationDate: java.time.OffsetDateTime,

    @field:Valid
    @Schema(example = "null", description = "")
    @get:JsonProperty("from") val from: LocationDto? = null,

    @field:Valid
    @Schema(example = "null", description = "")
    @get:JsonProperty("to") val to: LocationDto? = null,

    @get:DecimalMin("1")
    @Schema(example = "null", description = "Дистанция до маршрута")
    @get:JsonProperty("distance") val distance: kotlin.Double? = null
    ) {

}

