package com.ifmo.se.navigator.mappers

import com.ifmo.se.navigator.models.Location
import com.ifmo.se.navigator.models.LocationResponse
import generated.com.ifmo.se.navigator.dto.LocationResponseDto
import generated.com.ifmo.se.navigator.dto.LocationDto as NavigatorLocationDto

internal fun Location.toNavigatorLocationDto() = NavigatorLocationDto(
    x = x,
    y = y,
    z = z,
    name = name,
)

internal fun LocationResponse.toLocationResponseDto() = LocationResponseDto(
    total = total,
    locations = locations?.map { it.toNavigatorLocationDto() },
)
