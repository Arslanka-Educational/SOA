package com.ifmo.se.navigator.ejb.services

import com.ifmo.se.navigator.ejb.clients.RouteManagementClientInterface
import com.ifmo.se.navigator.ejb.mappers.toDomain
import com.ifmo.se.navigator.models.Location
import com.ifmo.se.navigator.models.LocationId
import com.ifmo.se.navigator.models.LocationResponse
import jakarta.ejb.EJB
import jakarta.ejb.Stateless

@Stateless
class LocationManagementService {

    @EJB
    private lateinit var routeManagementClient: RouteManagementClientInterface

    internal fun getLocationById(locationId: LocationId): Location? =
        toDomain(routeManagementClient.getLocationById(id = locationId.id))

    fun getLocations(limit: Int?, offset: Int?): LocationResponse? =
        routeManagementClient.getLocations(
            limit = limit,
            offset = offset
        ).let { toDomain(it) }
}