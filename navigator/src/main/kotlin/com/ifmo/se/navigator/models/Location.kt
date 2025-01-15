package com.ifmo.se.navigator.models

data class Location(
    val x: Int,
    val y: Int?,
    val z: Long,
    val name: String?,
)

@JvmInline
value class LocationId(val id: Long)
