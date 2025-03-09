package com.ifmo.se.navigator.dtos

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.DecimalMax

/**
 * 
 * @param x 
 * @param y 
 */
data class CoordinatesDto(

    @Schema(example = "null", required = true, description = "")
    @get:JsonProperty("x", required = true) val x: Int,

    @get:DecimalMax("610")
    @Schema(example = "null", required = true, description = "")
    @get:JsonProperty("y", required = true) val y: Double
    ) {

}

