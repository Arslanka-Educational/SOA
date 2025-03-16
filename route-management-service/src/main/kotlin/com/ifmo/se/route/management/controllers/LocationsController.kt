package org.example.com.ifmo.se.route.management.controllers

import com.ifmo.se.route.management.wsdl.LocationDto
import com.ifmo.se.route.management.wsdl.LocationResponseDto
import jakarta.jws.WebMethod
import jakarta.jws.WebParam
import jakarta.jws.WebService
import lombok.RequiredArgsConstructor
import mu.KLogging
import org.example.com.ifmo.se.route.management.services.LocationService
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller

@RequiredArgsConstructor
@WebService(
    serviceName = "LocationsPortService",
    targetNamespace = "http://ifmo.com/se/route-management",
)
@Controller
open class LocationsController(
    private val locationService: LocationService,
) {
    private companion object : KLogging()

    @WebMethod(operationName = "getLocationById")
    fun getLocationById(
        @WebParam(name = "id") id: Long
    ): LocationDto? = locationService.getLocationById(id)

    @WebMethod(operationName = "getLocations")
    fun getLocations(
        @WebParam(name = "limit") limit: Int?,
        @WebParam(name = "offset") offset: Int?
    ): LocationResponseDto? =
        locationService.getLocations(
            limit = limit,
            offset = offset
        )
}