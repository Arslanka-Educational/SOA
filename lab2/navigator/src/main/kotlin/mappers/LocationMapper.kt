package ru.openbook.mappers

import ru.openbook.models.Location
import ru.route.dto.LocationDto
import ru.navigator.dto.LocationDto as NavigatorLocationDto

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