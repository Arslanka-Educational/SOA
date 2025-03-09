package com.ifmo.se.navigator.dtos

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.Valid
import io.swagger.v3.oas.annotations.media.Schema
import java.io.Serializable

/**
 * 
 * @param locations 
 * @param total Общее количество элементов
 */
data class LocationResponseDto(

    @get:JsonProperty("locations") val locations: List<LocationDto>? = null,

    @get:JsonProperty("total") val total: Int? = null
    ): Serializable
