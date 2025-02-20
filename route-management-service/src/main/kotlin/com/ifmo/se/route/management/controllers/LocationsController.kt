package org.example.com.ifmo.se.route.management.controllers

import generated.com.ifmo.se.route.api.LocationsApi
import generated.com.ifmo.se.route.dto.LocationDto
import lombok.RequiredArgsConstructor
import org.example.com.ifmo.se.route.management.services.LocationService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RequiredArgsConstructor
@RestController
open class LocationsController(
    private val locationService: LocationService,
) : LocationsApi {
    override fun getLocationById(id: Long): ResponseEntity<LocationDto> {
        return ResponseEntity.ok(locationService.getLocationById(id))
    }
}