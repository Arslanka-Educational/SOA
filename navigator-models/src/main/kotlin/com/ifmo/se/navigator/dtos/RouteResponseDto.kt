package com.ifmo.se.navigator.dtos

import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable

/**
 * 
 * @param routes 
 * @param total Общее количество элементов
 * @param limit Количество элементов
 * @param offset Сдвиг по элементам
 */
data class RouteResponseDto(

    @get:JsonProperty("routes") var routes: List<RouteDto>? = null,

    @get:JsonProperty("total") var total: Int? = null,

    @get:JsonProperty("limit") var limit: Int? = null,

    @get:JsonProperty("offset") var offset: Int? = null
    ): Serializable
