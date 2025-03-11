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

    @get:JsonProperty("id", required = true) var id: Long? = null,

    @get:JsonProperty("name", required = true) var name: String? = null,

    @get:JsonProperty("coordinates", required = true) var coordinates: CoordinatesDto? = null,

    @get:JsonProperty("creationDate", required = true) var creationDate: java.time.OffsetDateTime? = null,

    @get:JsonProperty("from") var from: LocationDto? = null,

    @get:JsonProperty("to") var to: LocationDto? = null,

    @get:JsonProperty("distance") var distance: Double? = null
    ): Serializable

