package com.ifmo.se.navigator.dtos

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.Valid

/**
 * 
 * @param routes 
 * @param total Общее количество элементов
 * @param limit Количество элементов
 * @param offset Сдвиг по элементам
 */
data class RouteResponseDto(

    @field:Valid
    @Schema(example = "null", description = "")
    @get:JsonProperty("routes") val routes: kotlin.collections.List<RouteDto>? = null,

    @Schema(example = "null", description = "Общее количество элементов")
    @get:JsonProperty("total") val total: kotlin.Int? = null,

    @Schema(example = "null", description = "Количество элементов")
    @get:JsonProperty("limit") val limit: kotlin.Int? = null,

    @Schema(example = "null", description = "Сдвиг по элементам")
    @get:JsonProperty("offset") val offset: kotlin.Int? = null
    ) {

}

