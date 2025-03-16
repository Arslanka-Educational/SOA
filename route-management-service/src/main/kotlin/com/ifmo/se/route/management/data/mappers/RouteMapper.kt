package org.example.com.ifmo.se.route.management.data.mappers

import com.ifmo.se.route.management.wsdl.CoordinatesDto
import com.ifmo.se.route.management.wsdl.LocationDto
import com.ifmo.se.route.management.wsdl.RouteDto
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

internal fun Route.mapToDto() = RouteDto().apply {
    creationDate = offsetDateTimeToXmlGregorianCalendar(
        OffsetDateTime.ofInstant(
            this@mapToDto.creationDate,
            java.time.ZoneOffset.UTC
        )
    )
    id = this@mapToDto.id.toInt()
    coordinates = this@mapToDto.coordinates.mapToDto()
    from = this@mapToDto.from?.mapToDto()
    to = this@mapToDto.to?.mapToDto()
    distance = this@mapToDto.distance
    name = this@mapToDto.name
}

internal fun CoordinatesDto.mapToEntity() = Coordinates(
    x = x,
    y = y
)

internal fun Coordinates.mapToDto() = CoordinatesDto().apply {
    x = this@mapToDto.x
    y = this@mapToDto.y
}