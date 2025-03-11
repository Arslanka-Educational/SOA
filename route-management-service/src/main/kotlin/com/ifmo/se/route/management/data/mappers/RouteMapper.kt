package org.example.com.ifmo.se.route.management.data.mappers

import generated.com.ifmo.se.route.dto.CoordinatesDto
import generated.com.ifmo.se.route.dto.LocationDto
import generated.com.ifmo.se.route.dto.RouteDto
import mapToDto
import org.example.com.ifmo.se.route.management.data.models.Coordinates
import org.example.com.ifmo.se.route.management.data.models.Location
import org.example.com.ifmo.se.route.management.data.models.Route
import java.time.OffsetDateTime


internal fun LocationDto.mapToEntity() = Location(
    x = x,
    y = y,
    z = z,
    name = name
)

internal fun Route.mapToDto() = RouteDto(
    creationDate = OffsetDateTime.ofInstant(creationDate, java.time.ZoneOffset.UTC),
    id = id,
    coordinates = coordinates.mapToDto(),
    from = from?.mapToDto(),
    to = to?.mapToDto(),
    distance = distance,
    name = name
)

internal fun CoordinatesDto.mapToEntity() = Coordinates(
    x = x,
    y = y
)

internal fun Coordinates.mapToDto() = CoordinatesDto(
    x = x,
    y = y
)