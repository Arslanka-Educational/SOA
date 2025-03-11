package com.ifmo.se.navigator.models

import java.io.Serializable

data class Location(
    var x: Int?,
    var y: Int?,
    var z: Long?,
    var name: String?,
    var id: Int?
): Serializable

data class LocationResponse(
    var total: Int?,
    var locations: List<Location>?,
): Serializable

@JvmInline
value class LocationId(val id: Long): Serializable
