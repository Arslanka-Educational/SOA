package com.ifmo.se.navigator.dtos

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.Valid
import java.io.Serializable

/**
 * 
 * @param routes 
 * @param total Общее количество элементов
 * @param limit Количество элементов
 * @param offset Сдвиг по элементам
 */
data class RouteResponseDto(

    @get:JsonProperty("routes") val routes: kotlin.collections.List<RouteDto>? = null,

    @get:JsonProperty("total") val total: kotlin.Int? = null,

    @get:JsonProperty("limit") val limit: kotlin.Int? = null,

    @get:JsonProperty("offset") val offset: kotlin.Int? = null
    ): Serializable
