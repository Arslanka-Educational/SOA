package com.ifmo.se.navigator.models

data class Location(
    val x: Int,
    val y: Int?,
    val z: Long,
    val name: String?,
    val id: Int?
)

data class LocationResponse(
    val total: Int?,
    val locations: List<Location>?,
)

@JvmInline
value class LocationId(val id: Long)
