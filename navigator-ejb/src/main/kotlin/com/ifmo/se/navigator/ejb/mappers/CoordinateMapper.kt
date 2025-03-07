package com.ifmo.se.navigator.ejb.mappers

import com.ifmo.se.navigator.models.Coordinate
import generated.com.ifmo.se.route.dto.CoordinatesDto

internal fun Coordinate.toRouteCoordinatesDto() = CoordinatesDto(
    x = x,
    y = y,
)

internal fun toDomain(coordinatesDto: CoordinatesDto) = Coordinate(x = coordinatesDto.x, y = coordinatesDto.y)