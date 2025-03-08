package com.ifmo.se.navigator.ejb.services

import com.ifmo.se.navigator.ejb.clients.RouteManagementClientInterface
import com.ifmo.se.navigator.ejb.mappers.toDomain
import com.ifmo.se.navigator.models.Location
import com.ifmo.se.navigator.models.LocationId
import com.ifmo.se.navigator.models.LocationResponse
import jakarta.ejb.EJB
import jakarta.ejb.Startup
import jakarta.ejb.Stateless

@Stateless
@Startup
open class LocationManagementService : LocationManagementServiceInterface {

    @EJB
    private lateinit var routeManagementClient: RouteManagementClientInterface

    override fun getLocationById(locationId: LocationId): Location? =
        toDomain(routeManagementClient.getLocationById(id = locationId.id))

    override fun getLocations(limit: Int?, offset: Int?): LocationResponse? =
        routeManagementClient.getLocations(
            limit = limit,
            offset = offset
        ).let { toDomain(it) }
}