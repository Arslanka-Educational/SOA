package org.example.com.ifmo.se.route.management.controllers

import generated.com.ifmo.se.route.api.LocationsApi
import generated.com.ifmo.se.route.dto.LocationDto
import generated.com.ifmo.se.route.dto.LocationResponseDto
import jakarta.jws.WebMethod
import jakarta.jws.WebParam
import jakarta.jws.WebService
import kotlinx.coroutines.runBlocking
import lombok.RequiredArgsConstructor
import mu.KLogging
import org.example.com.ifmo.se.route.management.services.LocationService
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller

@RequiredArgsConstructor
@WebService(serviceName = "LocationsController")
@Controller
open class LocationsController(
    private val locationService: LocationService,
) {
    private companion object : KLogging()

    @WebMethod(operationName = "getLocationById")
    fun getLocationById(
        @WebParam(name = "id") id: Long
    ): ResponseEntity<LocationDto> = ResponseEntity.ok(locationService.getLocationById(id))

    @WebMethod(operationName = "getLocations")
    fun getLocations(
        @WebParam(name = "limit") limit: Int?,
        @WebParam(name = "offset") offset: Int?
    ): ResponseEntity<LocationResponseDto> =
        ResponseEntity.ok(
            locationService.getLocations(
                limit = limit,
                offset = offset
            )
        )
}