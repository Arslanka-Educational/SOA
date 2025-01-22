package org.example.com.ifmo.se.route.management.services

import generated.com.ifmo.se.route.dto.LocationDto

interface LocationService {
    fun getLocationById(locationId: Long): LocationDto?
}