package com.ifmo.se.navigator.services

import com.ifmo.se.navigator.models.*
import generated.com.ifmo.se.route.dto.GetRoutesFilterParameterDto
import generated.com.ifmo.se.route.dto.SortFieldsDto
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import org.springframework.stereotype.Service

@Service
class NavigatorService(
    private val locationManagementService: LocationManagementService,
    private val routeManagementService: RouteManagementService,
) {
    internal suspend fun addRoute(
        idFrom: LocationId,
        idTo: LocationId,
        distance: Double,
        name: String,
        coordinate: Coordinate?,
    ): EnrichedRoute =
        coroutineScope {
            val locationFromDeferred = async { locationManagementService.getLocationById(locationId = idFrom) }
            val locationToDeferred = async { locationManagementService.getLocationById(locationId = idTo) }
            routeManagementService.addRoute(
                route = Route(
                    name = name,
                    coordinate = coordinate,
                    locationFrom = locationFromDeferred.await(),
                    locationTo = locationToDeferred.await(),
                    distance = distance,
                )
            )
        }

    internal suspend fun getRoutesBetweenLocations(
        idFrom: LocationId,
        idTo: LocationId,
        shortest: Boolean,
    ): List<EnrichedRoute>? {
        val filterParameters = GetRoutesFilterParameterDto(
            locationIdFrom = idFrom.id,
            locationIdTo = idTo.id,
        )

        val sortField = if (shortest) {
            SortFieldsDto.Distance
        } else {
            SortFieldsDto.MinusDistance
        }

        return routeManagementService.getRoutes(
            filter = filterParameters,
            sortBy = listOf(sortField),
            limit = 10,
            offset = 0,
        )
    }
}