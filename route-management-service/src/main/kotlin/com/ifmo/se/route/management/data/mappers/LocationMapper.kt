package org.example.com.ifmo.se.route.management.data.mappers

import generated.com.ifmo.se.route.dto.LocationDto
import org.example.com.ifmo.se.route.management.configs.MapperConfiguration
import org.example.com.ifmo.se.route.management.data.models.Location
import org.mapstruct.Mapper

@Mapper(config = MapperConfiguration::class)
interface LocationMapper {
    fun map(entity: Location): LocationDto
    fun map(entity: LocationDto): Location
}