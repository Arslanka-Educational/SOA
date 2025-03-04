package org.example.com.ifmo.se.route.management.data.mappers

import generated.com.ifmo.se.route.dto.CoordinatesDto
import generated.com.ifmo.se.route.dto.LocationDto
import generated.com.ifmo.se.route.dto.RouteDto
import generated.com.ifmo.se.route.dto.RouteUpsertRequestDto
import org.example.com.ifmo.se.route.management.configs.MapperConfiguration
import org.example.com.ifmo.se.route.management.data.models.Coordinates
import org.example.com.ifmo.se.route.management.data.models.Location
import org.example.com.ifmo.se.route.management.data.models.Route
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper(config = MapperConfiguration::class)
interface RouteMapper {
    fun map(entity: Location): LocationDto
    fun map(entity: LocationDto): Location

    @Mapping(
        target = "creationDate",
        expression = "java(java.time.OffsetDateTime.ofInstant(entity.getCreationDate(), java.time.ZoneOffset.UTC))"
    )
    fun map(entity: Route): RouteDto
    fun map(entity: RouteUpsertRequestDto): Route

    fun map(entity: CoordinatesDto): Coordinates
    fun map(entity: Coordinates): CoordinatesDto
}