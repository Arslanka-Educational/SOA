package com.ifmo.se.navigator.mappers

import com.ifmo.se.navigator.models.EnrichedRoute
import generated.com.ifmo.se.navigator.dto.RouteResponseDto
import generated.com.ifmo.se.navigator.dto.RouteDto as NavigatorRouteDto

internal fun List<EnrichedRoute>.toRoutesResponse() = RouteResponseDto(
    total = this.size,
    routes = this.map { it.toNavigatorRouteDto() },
    limit = 10,
    offset = 0
)

internal fun EnrichedRoute.toNavigatorRouteDto() = NavigatorRouteDto(
    id = this.id!!,
    name = this.name!!,
    coordinates = this.coordinate?.toNavigatorCoordinatesDto(),
    from = this.locationFrom?.toNavigatorLocationDto(),
    to = this.locationTo?.toNavigatorLocationDto(),
    distance = this.distance,
    creationDate = this.creationDate!!,
)
