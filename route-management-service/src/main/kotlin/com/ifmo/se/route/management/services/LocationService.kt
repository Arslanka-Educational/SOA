package org.example.com.ifmo.se.route.management.services

import generated.com.ifmo.se.route.dto.LocationDto
import generated.com.ifmo.se.route.dto.LocationResponseDto

interface LocationService {
    fun getLocationById(locationId: Long): LocationDto?

    fun getLocations(limit: Int?, offset: Int?): LocationResponseDto?
}