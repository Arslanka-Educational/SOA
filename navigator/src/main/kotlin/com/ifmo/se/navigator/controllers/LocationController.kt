package com.ifmo.se.navigator.com.ifmo.se.navigator.controllers

import com.ifmo.se.navigator.services.LocationManagementService
import generated.com.ifmo.se.navigator.api.LocationsApi
import generated.com.ifmo.se.navigator.dto.LocationResponseDto
import kotlinx.coroutines.runBlocking
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
open class LocationController(
    private val locationManagementService: LocationManagementService,
) : LocationsApi {
    override fun getLocations(
        limit: Int?,
        offset: Int?
    ): ResponseEntity<LocationResponseDto> = runBlocking {
        ResponseEntity.ok(locationManagementService.getLocations(limit, offset))
    }
}