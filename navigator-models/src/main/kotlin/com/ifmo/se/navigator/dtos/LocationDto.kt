package com.ifmo.se.navigator.dtos

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Size
import java.io.Serializable

/**
 * 
 * @param x 
 * @param z 
 * @param y 
 * @param name 
 * @param id 
 */
data class LocationDto(

    @get:JsonProperty("x", required = true) val x: kotlin.Int,

    @get:JsonProperty("z", required = true) val z: kotlin.Long,

    @get:JsonProperty("y") val y: kotlin.Int? = null,

    @get:JsonProperty("name") val name: kotlin.String? = null,

    @get:JsonProperty("id") val id: kotlin.Int? = null
    ): Serializable

