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

    @get:JsonProperty("x", required = true) var x: Int? = null,

    @get:JsonProperty("z", required = true) var z: Long? = null,

    @get:JsonProperty("y") var y: Int? = null,

    @get:JsonProperty("name") var name: String? = null,

    @get:JsonProperty("id") var id: Int? = null
    ): Serializable

