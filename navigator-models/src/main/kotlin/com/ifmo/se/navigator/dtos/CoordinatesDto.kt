package com.ifmo.se.navigator.dtos

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.DecimalMax
import java.io.Serializable

/**
 * 
 * @param x 
 * @param y 
 */
data class CoordinatesDto(

    @get:JsonProperty("x", required = true) var x: Int? = null,

    @get:JsonProperty("y", required = true) var y: Double? = null
): Serializable

