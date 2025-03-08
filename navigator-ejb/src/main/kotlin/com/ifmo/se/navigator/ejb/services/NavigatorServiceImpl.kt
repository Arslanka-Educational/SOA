package com.ifmo.se.navigator.ejb.services

import com.ifmo.se.navigator.models.Coordinate
import com.ifmo.se.navigator.models.EnrichedRoute
import com.ifmo.se.navigator.models.LocationId
import com.ifmo.se.navigator.models.Route
import generated.com.ifmo.se.route.dto.GetRoutesFilterParameterDto
import generated.com.ifmo.se.route.dto.SortFieldsDto
import jakarta.ejb.Startup
import jakarta.ejb.Stateless
import jakarta.inject.Inject

@Stateless(name = "NavigatorServiceBean")
@Startup
open class NavigatorServiceImpl @Inject constructor(
    private val locationManagementServiceImpl: LocationManagementService,
    private val routeManagementServiceImpl: RouteManagementService,
): NavigatorService {


    override fun addRoute(
        idFrom: LocationId,
        idTo: LocationId,
        distance: Double,
        name: String,
        coordinate: Coordinate?
    ): EnrichedRoute {
        val locationFrom = locationManagementServiceImpl.getLocationById(idFrom)
        val locationTo = locationManagementServiceImpl.getLocationById(idTo)

        return routeManagementServiceImpl.addRoute(
            Route(
                name = name,
                coordinate = coordinate,
                locationFrom = locationFrom,
                locationTo = locationTo,
                distance = distance
            )
        )
    }

    override fun getRoutesBetweenLocations(
        idFrom: LocationId,
        idTo: LocationId,
        shortest: Boolean
    ): List<EnrichedRoute>? {
        val filterParameters = GetRoutesFilterParameterDto(
            locationIdFrom = idFrom.id,
            locationIdTo = idTo.id
        )

        val sortField = if (shortest) {
            SortFieldsDto.Distance
        } else {
            SortFieldsDto.MinusDistance
        }

        return routeManagementServiceImpl.getRoutes(
            filter = filterParameters,
            sortBy = listOf(sortField),
            limit = 10,
            offset = 0
        )
    }
}