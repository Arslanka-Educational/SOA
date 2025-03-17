package com.ifmo.se.navigator.dtos

import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable

/**
 * 
 * @param locations 
 * @param total Общее количество элементов
 */
data class LocationResponseDto(

    @get:JsonProperty("locations") var locations: List<LocationDto>? = null,

    @get:JsonProperty("total") var total: Int? = null
    ): Serializable
