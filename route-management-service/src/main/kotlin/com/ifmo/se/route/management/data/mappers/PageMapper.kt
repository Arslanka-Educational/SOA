package org.example.com.ifmo.se.route.management.data.mappers

import generated.com.ifmo.se.route.dto.LocationResponseDto
import org.example.com.ifmo.se.route.management.data.models.Location
import org.springframework.data.domain.Page

internal fun Page<Location>.toResponse(locationMapper: LocationMapper) = LocationResponseDto(
    total = totalElements.toInt(),
    locations = content.toList().map { locationMapper.map(it) }
)