package org.example.com.ifmo.se.route.management.services

import com.ifmo.se.route_management.LocationDto
import com.ifmo.se.route_management.LocationResponseDto


interface LocationService {
    fun getLocationById(locationId: Long): LocationDto?
    fun getLocations(limit: Int?, offset: Int?): LocationResponseDto?
}