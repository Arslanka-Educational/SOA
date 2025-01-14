package ru.openbook.services

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import org.springframework.stereotype.Service
import ru.openbook.models.EnrichedRoute
import ru.openbook.models.LocationId
import ru.openbook.models.Route

@Service
class NavigatorService(
    private val locationManagementService: LocationManagementService,
    private val routeManagementService: RouteManagementService,
) {
    internal suspend fun addRoute(idFrom: LocationId, idTo: LocationId, distance: Double, name: String): EnrichedRoute =
        coroutineScope {
            val locationFromDeferred = async { locationManagementService.getLocationById(locationId = idFrom) }
            val locationToDeferred = async { locationManagementService.getLocationById(locationId = idTo) }
            routeManagementService.addRoute(
                route = Route(
                    name = name,
                    coordinate = null,
                    locationFrom = locationFromDeferred.await(),
                    locationTo = locationToDeferred.await(),
                    distance = distance,
                )
            )
        }
}