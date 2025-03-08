package com.ifmo.se.navigator.ejb.services

import RouteManagementClientImpl
import com.ifmo.se.navigator.ejb.clients.RouteManagementClientInterface
import com.ifmo.se.navigator.ejb.mappers.toDomain
import com.ifmo.se.navigator.models.Location
import com.ifmo.se.navigator.models.LocationId
import com.ifmo.se.navigator.models.LocationResponse
import jakarta.ejb.Startup
import jakarta.ejb.Stateless
import jakarta.inject.Inject

@Stateless(name = "LocationManagementServiceBean")
@Startup
open class LocationManagementServiceImpl @Inject constructor(
    private val routeManagementClientImpl: RouteManagementClientInterface,
) : LocationManagementService {

    override fun getLocationById(locationId: LocationId): Location? =
        toDomain(routeManagementClientImpl.getLocationById(id = locationId.id))

    override fun getLocations(limit: Int?, offset: Int?): LocationResponse? =
        routeManagementClientImpl.getLocations(
            limit = limit,
            offset = offset
        ).let { toDomain(it) }
}