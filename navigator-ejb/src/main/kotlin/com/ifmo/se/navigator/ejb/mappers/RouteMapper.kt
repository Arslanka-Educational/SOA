package com.ifmo.se.navigator.ejb.mappers

import com.ifmo.se.navigator.dtos.RouteDto
import com.ifmo.se.navigator.dtos.RouteUpsertRequestDto
import com.ifmo.se.navigator.models.EnrichedRoute
import com.ifmo.se.navigator.models.Route

internal fun toRouteUpsertRequestDto(route: Route) = RouteUpsertRequestDto(
    name = route.name,
    coordinates = route.coordinate?.toRouteCoordinatesDto(),
    distance = route.distance,
    from = route.locationFrom?.toRouteLocationDto(),
    to = route.locationTo?.toRouteLocationDto(),
)

internal fun toDomain(route: RouteDto) = EnrichedRoute(
    id = route.id,
    name = route.name,
    coordinate = route.coordinates?.let { toDomain(it) },
    locationFrom = route.from?.let { toDomain(it) },
    locationTo = route.to?.let { toDomain(it) },
    distance = route.distance,
    creationDate = route.creationDate,
)
