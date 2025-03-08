package com.ifmo.se.navigator.com.ifmo.se.navigator.controllers

import com.ifmo.se.navigator.ejb.services.LocationManagementService
import com.ifmo.se.navigator.mappers.toLocationResponseDto
import generated.com.ifmo.se.navigator.api.LocationsApi
import generated.com.ifmo.se.navigator.dto.LocationResponseDto
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
open class LocationController(
    private val locationManagementServiceImpl: LocationManagementService,
) : LocationsApi {
    override fun getLocations(
        limit: Int?,
        offset: Int?
    ): ResponseEntity<LocationResponseDto> =
        ResponseEntity.ok(locationManagementServiceImpl.getLocations(limit, offset)?.toLocationResponseDto())
}