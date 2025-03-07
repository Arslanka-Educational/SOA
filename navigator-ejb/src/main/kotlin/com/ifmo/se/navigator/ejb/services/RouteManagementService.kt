package com.ifmo.se.navigator.ejb.services

import com.ifmo.se.navigator.ejb.clients.RouteManagementClientInterface
import com.ifmo.se.navigator.ejb.mappers.toDomain
import com.ifmo.se.navigator.ejb.mappers.toRouteUpsertRequestDto
import com.ifmo.se.navigator.models.EnrichedRoute
import com.ifmo.se.navigator.models.Route
import generated.com.ifmo.se.route.dto.GetRoutesFilterParameterDto
import generated.com.ifmo.se.route.dto.SortFieldsDto
import jakarta.ejb.EJB
import jakarta.ejb.Startup
import jakarta.ejb.Stateless

@Stateless
@Startup
class RouteManagementService {

    @EJB
    private lateinit var routeManagementClient: RouteManagementClientInterface

    internal fun addRoute(route: Route): EnrichedRoute =
        toDomain(routeManagementClient.addRoute(toRouteUpsertRequestDto(route)))

    internal fun getRoutes(
        limit: Int?,
        offset: Int?,
        sortBy: List<SortFieldsDto>?,
        filter: GetRoutesFilterParameterDto?
    ): List<EnrichedRoute>? =
        routeManagementClient.getRoutes(filter, sortBy, limit, offset).routes?.map { toDomain(it) }
}