package com.ifmo.se.navigator.services

import com.ifmo.se.navigator.mappers.toDomain
import com.ifmo.se.navigator.mappers.toRouteUpsertRequestDto
import com.ifmo.se.navigator.models.EnrichedRoute
import com.ifmo.se.navigator.models.Route
import generated.com.ifmo.se.route.api.RoutesApi
import generated.com.ifmo.se.route.dto.GetRoutesFilterParameterDto
import generated.com.ifmo.se.route.dto.SortFieldsDto
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

    internal suspend fun getRouteFirstMatched(
        limit: Int?,
        offset: Int?,
        sortBy: List<SortFieldsDto>?,
        filter: GetRoutesFilterParameterDto?,
    ): EnrichedRoute? =
        routesApi.getRoutes(filter = filter, sortBy = sortBy, limit = limit, offset = offset)
            .body().routes?.firstOrNull()
            ?.toDomain().also {
                logger.info { "Get route=$it by filter = $filter, sortBy = $sortBy, limit = $limit, offset = $offset" }
            } // TODO(null or 404?)
}