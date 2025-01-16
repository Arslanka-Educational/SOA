package com.ifmo.se.navigator.services

import com.ifmo.se.navigator.models.Coordinate
import com.ifmo.se.navigator.models.EnrichedRoute
import com.ifmo.se.navigator.models.LocationId
import com.ifmo.se.navigator.models.Route
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
}