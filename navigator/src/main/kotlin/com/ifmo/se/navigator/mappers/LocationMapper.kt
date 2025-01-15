package com.ifmo.se.navigator.mappers

import com.ifmo.se.navigator.models.Location
import generated.com.ifmo.se.route.dto.LocationDto
import generated.com.ifmo.se.navigator.dto.LocationDto as NavigatorLocationDto

internal fun Location.toRouteLocationDto() = LocationDto(
    x = x,
    y = y,
    z = z,
    name = name,
)

internal fun Location.toNavigatorLocationDto() = NavigatorLocationDto(
    x = x,
    y = y,
    z = z,
    name = name,
)

internal fun LocationDto.toDomain() = Location(
    x = this.x,
    y = this.y,
    z = this.z,
    name = this.name,
)