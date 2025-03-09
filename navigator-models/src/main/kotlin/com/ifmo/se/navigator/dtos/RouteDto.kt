package com.ifmo.se.navigator.dtos

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.Valid
import jakarta.validation.constraints.DecimalMin
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.Size
import java.io.Serializable

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

    @get:JsonProperty("id", required = true) val id: kotlin.Long,

    @get:JsonProperty("name", required = true) val name: kotlin.String,

    @get:JsonProperty("coordinates", required = true) val coordinates: CoordinatesDto?,

    @get:JsonProperty("creationDate", required = true) val creationDate: java.time.OffsetDateTime,

    @get:JsonProperty("from") val from: LocationDto? = null,

    @get:JsonProperty("to") val to: LocationDto? = null,

    @get:JsonProperty("distance") val distance: kotlin.Double? = null
    ): Serializable

