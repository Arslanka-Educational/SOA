package com.ifmo.se.navigator.dtos

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Size

/**
 * 
 * @param x 
 * @param z 
 * @param y 
 * @param name 
 * @param id 
 */
data class LocationDto(

    @Schema(example = "null", required = true, description = "")
    @get:JsonProperty("x", required = true) val x: kotlin.Int,

    @Schema(example = "null", required = true, description = "")
    @get:JsonProperty("z", required = true) val z: kotlin.Long,

    @Schema(example = "null", description = "")
    @get:JsonProperty("y") val y: kotlin.Int? = null,

    @get:Size(max=517)
    @Schema(example = "null", description = "")
    @get:JsonProperty("name") val name: kotlin.String? = null,

    @Schema(example = "null", description = "")
    @get:JsonProperty("id") val id: kotlin.Int? = null
    ) {

}

