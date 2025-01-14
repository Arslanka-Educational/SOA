package ru.openbook.services

import mu.KLogging
import org.springframework.stereotype.Service
import ru.openbook.mappers.toDomain
import ru.openbook.mappers.toRouteUpsertRequestDto
import ru.openbook.models.EnrichedRoute
import ru.openbook.models.Route
import ru.route.api.RoutesApi

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