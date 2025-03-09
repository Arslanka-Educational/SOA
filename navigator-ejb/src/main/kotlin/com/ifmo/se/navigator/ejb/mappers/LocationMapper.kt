package com.ifmo.se.navigator.ejb.mappers

import com.ifmo.se.navigator.dtos.LocationDto
import com.ifmo.se.navigator.models.Location
import com.ifmo.se.navigator.models.LocationResponse
import com.ifmo.se.navigator.dtos.LocationResponseDto

internal fun Location.toRouteLocationDto() = LocationDto(
    x = x,
    y = y,
    z = z,
    name = name,
    id = id,
)

internal fun toDomain(locationDto: LocationDto) = Location(
    x = locationDto.x,
    y = locationDto.y,
    z = locationDto.z,
    name = locationDto.name,
    id = locationDto.id,
)

internal fun toDomain(locationResponseDto: LocationResponseDto) = LocationResponse(
    total = locationResponseDto.total,
    locations = locationResponseDto.locations?.map { toDomain(it) },
)