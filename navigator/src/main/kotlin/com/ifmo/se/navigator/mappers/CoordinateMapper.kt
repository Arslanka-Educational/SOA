package com.ifmo.se.navigator.mappers

import com.ifmo.se.navigator.models.Coordinate
import generated.com.ifmo.se.route.dto.CoordinatesDto
import generated.com.ifmo.se.navigator.dto.CoordinatesDto as NavigatorCoordinatesDto

internal fun Coordinate.toNavigatorCoordinatesDto() = NavigatorCoordinatesDto(
    x = x,
    y = y,
)
