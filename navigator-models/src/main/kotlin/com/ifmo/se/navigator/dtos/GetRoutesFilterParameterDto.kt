package com.ifmo.se.navigator.dtos

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.Size
import java.time.OffsetDateTime

/**
 * 
 * @param locationIdFrom Id локации откуда
 * @param locationIdTo Id локации куда
 * @param minId Минимальный id маршрута
 * @param maxId Максимальный id маршрута
 * @param name Название маршрута
 * @param minX Минимальная координата х
 * @param maxX Максимальная координата х
 * @param minY Минимальная координата y
 * @param maxY Максимальная координата y
 * @param minCreationDate Дата создания должна быть позже
 * @param maxCreationDate Дата создания должна быть раньше или равна
 * @param fromLocationX Минимальное координата X откуда
 * @param toLocationX Максимальная координата X откуда
 * @param fromLocationY Минимальное координата Y откуда
 * @param toLocationY Максимальная координата Y откуда
 * @param fromLocationZ Координата Z откуда
 * @param toLocationZ Координата Z куда
 * @param distance Дистанция маршрута
 * @param nameStartsWithSubstr Имя маршрута начинается с заданной подстроки
 */
data class GetRoutesFilterParameterDto(

    @get:Min(1L)
    @Schema(example = "25", description = "Id локации откуда")
    @get:JsonProperty("locationIdFrom") val locationIdFrom: Long? = null,

    @get:Min(1L)
    @Schema(example = "25", description = "Id локации куда")
    @get:JsonProperty("locationIdTo") val locationIdTo: Long? = null,

    @get:Min(1)
    @Schema(example = "25", description = "Минимальный id маршрута")
    @get:JsonProperty("minId") val minId: Int? = null,

    @get:Min(1)
    @Schema(example = "52", description = "Максимальный id маршрута")
    @get:JsonProperty("maxId") val maxId: Int? = null,

    @get:Size(min=1)
    @Schema(example = "null", description = "Название маршрута")
    @get:JsonProperty("name") val name: String? = null,

    @Schema(example = "-1", description = "Минимальная координата х")
    @get:JsonProperty("minX") val minX: Int? = null,

    @Schema(example = "1", description = "Максимальная координата х")
    @get:JsonProperty("maxX") val maxX: Int? = null,

    @Schema(example = "-1337.3", description = "Минимальная координата y")
    @get:JsonProperty("minY") val minY: Double? = null,

    @Schema(example = "1337.3", description = "Максимальная координата y")
    @get:JsonProperty("maxY") val maxY: Double? = null,

    @Schema(example = "2003-10-17T11:02:15Z", description = "Дата создания должна быть позже")
    @get:JsonProperty("minCreationDate") val minCreationDate: OffsetDateTime? = null,

    @Schema(example = "2024-10-17T11:02:15Z", description = "Дата создания должна быть раньше или равна")
    @get:JsonProperty("maxCreationDate") val maxCreationDate: OffsetDateTime? = null,

    @Schema(example = "null", description = "Минимальное координата X откуда")
    @get:JsonProperty("fromLocationX") val fromLocationX: Int? = null,

    @Schema(example = "null", description = "Максимальная координата X откуда")
    @get:JsonProperty("toLocationX") val toLocationX: Int? = null,

    @Schema(example = "null", description = "Минимальное координата Y откуда")
    @get:JsonProperty("fromLocationY") val fromLocationY: Int? = null,

    @Schema(example = "null", description = "Максимальная координата Y откуда")
    @get:JsonProperty("toLocationY") val toLocationY: Int? = null,

    @Schema(example = "null", description = "Координата Z откуда")
    @get:JsonProperty("fromLocationZ") val fromLocationZ: Long? = null,

    @Schema(example = "null", description = "Координата Z куда")
    @get:JsonProperty("toLocationZ") val toLocationZ: Long? = null,

    @Schema(example = "null", description = "Дистанция маршрута")
    @get:JsonProperty("distance") val distance: Double? = null,

    @get:Size(min=1)
    @Schema(example = "null", description = "Имя маршрута начинается с заданной подстроки")
    @get:JsonProperty("nameStartsWithSubstr") val nameStartsWithSubstr: String? = null
    ) {

}

