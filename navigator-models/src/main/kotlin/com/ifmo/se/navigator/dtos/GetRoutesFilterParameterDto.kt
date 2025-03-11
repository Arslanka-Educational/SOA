package com.ifmo.se.navigator.dtos

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.Size
import java.io.Serializable
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

    @get:JsonProperty("locationIdFrom") val locationIdFrom: Long? = null,

    @get:JsonProperty("locationIdTo") val locationIdTo: Long? = null,

    @get:JsonProperty("minId") val minId: Int? = null,

    @get:JsonProperty("maxId") val maxId: Int? = null,

    @get:JsonProperty("name") val name: String? = null,

    @get:JsonProperty("minX") val minX: Int? = null,

    @get:JsonProperty("maxX") val maxX: Int? = null,

    @get:JsonProperty("minY") val minY: Double? = null,

    @get:JsonProperty("maxY") val maxY: Double? = null,

    @get:JsonProperty("minCreationDate") val minCreationDate: OffsetDateTime? = null,

    @get:JsonProperty("maxCreationDate") val maxCreationDate: OffsetDateTime? = null,

    @get:JsonProperty("fromLocationX") val fromLocationX: Int? = null,

    @get:JsonProperty("toLocationX") val toLocationX: Int? = null,

    @get:JsonProperty("fromLocationY") val fromLocationY: Int? = null,

    @get:JsonProperty("toLocationY") val toLocationY: Int? = null,

    @get:JsonProperty("fromLocationZ") val fromLocationZ: Long? = null,

    @get:JsonProperty("toLocationZ") val toLocationZ: Long? = null,

    @get:JsonProperty("distance") val distance: Double? = null,

    @get:JsonProperty("nameStartsWithSubstr") val nameStartsWithSubstr: String? = null
): Serializable

