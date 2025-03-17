package org.example.com.ifmo.se.route.management.controllers

import com.ifmo.se.route_management.GetLocationByIdRequest
import com.ifmo.se.route_management.GetLocationByIdResponse
import com.ifmo.se.route_management.GetLocationsRequest
import com.ifmo.se.route_management.GetLocationsResponse
import com.ifmo.se.route_management.LocationDto
import com.ifmo.se.route_management.LocationResponseDto
import com.ifmo.se.route_management.PostRouteRequest
import jakarta.jws.WebMethod
import jakarta.jws.WebParam
import mu.KLogging
import org.example.com.ifmo.se.route.management.services.LocationService
import org.springframework.ws.server.endpoint.annotation.Endpoint
import org.springframework.ws.server.endpoint.annotation.PayloadRoot
import org.springframework.ws.server.endpoint.annotation.RequestPayload
import org.springframework.ws.server.endpoint.annotation.ResponsePayload

@Endpoint
class LocationsController(
    private val locationService: LocationService,
) {
    private companion object : KLogging()

    @PayloadRoot(namespace = Namespace.NAMESPACE, localPart = "getLocationById")
    @ResponsePayload
    fun getLocationById(
        @RequestPayload request: GetLocationByIdRequest,
    ): GetLocationByIdResponse = locationService.getLocationById(request.id).let {
        GetLocationByIdResponse().apply {
            this.location = let@ it
        }
    }

    @PayloadRoot(namespace = Namespace.NAMESPACE, localPart = "getLocations")
    @ResponsePayload
    fun getLocations(
        @RequestPayload request: GetLocationsRequest,
    ): GetLocationsResponse =
        locationService.getLocations(
            limit = request.limit,
            offset = request.offset,
        ).let {
            GetLocationsResponse().apply {
                this.locations = let@ it
            }
        }
}