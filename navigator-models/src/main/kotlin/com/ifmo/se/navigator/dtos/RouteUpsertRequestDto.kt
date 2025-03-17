package com.ifmo.se.navigator.dtos

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.Valid
import jakarta.validation.constraints.DecimalMin
import jakarta.validation.constraints.Size
import java.io.Serializable

/**
 * 
 * @param name 
 * @param coordinates 
 * @param distance Дистанция до маршрута
 * @param from 
 * @param to 
 */
data class RouteUpsertRequestDto(

    @get:JsonProperty("name", required = true) val name: kotlin.String? = null,

    @get:JsonProperty("coordinates", required = true) val coordinates: CoordinatesDto? = null,

    @get:JsonProperty("distance", required = true) val distance: kotlin.Double? = null,

    @get:JsonProperty("from") val from: LocationDto? = null,

    @get:JsonProperty("to") val to: LocationDto? = null
    ): Serializable

