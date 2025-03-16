package org.example.com.ifmo.se.route.management.services

import com.ifmo.se.route.management.wsdl.LocationDto
import com.ifmo.se.route.management.wsdl.LocationResponseDto


interface LocationService {
    fun getLocationById(locationId: Long): LocationDto?
    fun getLocations(limit: Int?, offset: Int?): LocationResponseDto?
}