package com.ifmo.se.navigator.dtos

import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable

/**
 * 
 * @param name 
 * @param coordinates 
 */
data class RouteAddRequestDto(

    @get:JsonProperty("name", required = true) var name: String? = null,

    @get:JsonProperty("coordinates") var coordinates: CoordinatesDto? = null
    ): Serializable

