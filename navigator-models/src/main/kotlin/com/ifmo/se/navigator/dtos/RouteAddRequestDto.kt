package com.ifmo.se.navigator.dtos

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.Valid
import io.swagger.v3.oas.annotations.media.Schema
import java.io.Serializable

/**
 * 
 * @param name 
 * @param coordinates 
 */
data class RouteAddRequestDto(

    @get:JsonProperty("name", required = true) val name: kotlin.String,

    @get:JsonProperty("coordinates") val coordinates: CoordinatesDto? = null
    ): Serializable

