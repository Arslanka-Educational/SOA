package com.ifmo.se.navigator.models

import java.time.OffsetDateTime

sealed class RouteBase {
    abstract val name: String
    abstract val coordinate: Coordinate?
    abstract val locationFrom: Location?
    abstract val locationTo: Location?
    abstract val distance: Double?
}

data class Route(
    override val name: String,
    override val coordinate: Coordinate?,
    override val locationFrom: Location?,
    override val locationTo: Location?,
    override val distance: Double,
) : RouteBase()

data class EnrichedRoute(
    val id: Long,
    override val name: String,
    override val coordinate: Coordinate?,
    override val locationFrom: Location?,
    override val locationTo: Location?,
    override val distance: Double,
    val creationDate: OffsetDateTime,
) : RouteBase()

