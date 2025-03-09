package com.ifmo.se.navigator.ejb.services

import RouteManagementClient
import com.ifmo.se.navigator.ejb.mappers.toDomain
import com.ifmo.se.navigator.models.Location
import com.ifmo.se.navigator.models.LocationId
import com.ifmo.se.navigator.models.LocationResponse
import jakarta.ejb.EJB
import jakarta.ejb.Stateless

@Stateless
open class LocationManagementServiceImpl: LocationManagementService {
    @EJB
    private lateinit var routeManagementClientImpl: RouteManagementClient

    override fun getLocationById(locationId: LocationId): Location? =
        toDomain(routeManagementClientImpl.getLocationById(id = locationId.id))

    override fun getLocations(limit: Int?, offset: Int?): LocationResponse? =
        routeManagementClientImpl.getLocations(
            limit = limit,
            offset = offset
        ).let { toDomain(it) }
}