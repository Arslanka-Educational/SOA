package com.ifmo.se.navigator.models

import java.io.Serializable

data class Location(
    val x: Int,
    val y: Int?,
    val z: Long,
    val name: String?,
    val id: Int?
): Serializable

data class LocationResponse(
    val total: Int?,
    val locations: List<Location>?,
): Serializable

@JvmInline
value class LocationId(val id: Long): Serializable
