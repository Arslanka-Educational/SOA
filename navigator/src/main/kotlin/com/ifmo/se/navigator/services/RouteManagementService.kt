package com.ifmo.se.navigator.services

import com.ifmo.se.navigator.mappers.toDomain
import com.ifmo.se.navigator.mappers.toRouteUpsertRequestDto
import com.ifmo.se.navigator.models.EnrichedRoute
import com.ifmo.se.navigator.models.Route
import generated.com.ifmo.se.route.api.RoutesApi
import mu.KLogging
import org.springframework.stereotype.Service

@Service
class RouteManagementService(
    private val routesApi: RoutesApi,
) {
    private companion object : KLogging()

    internal suspend fun addRoute(route: Route): EnrichedRoute =
        routesApi.postRoute(
            routeUpsertRequestDto = route.toRouteUpsertRequestDto(),
        ).body().toDomain().also {
            logger.info { "Added route=$it" }
        }
}