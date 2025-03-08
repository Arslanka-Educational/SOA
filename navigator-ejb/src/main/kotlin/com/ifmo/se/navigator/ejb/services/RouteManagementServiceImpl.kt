package com.ifmo.se.navigator.ejb.services

import RouteManagementClientImpl
import com.ifmo.se.navigator.ejb.clients.RouteManagementClientInterface
import com.ifmo.se.navigator.ejb.mappers.toDomain
import com.ifmo.se.navigator.ejb.mappers.toRouteUpsertRequestDto
import com.ifmo.se.navigator.models.EnrichedRoute
import com.ifmo.se.navigator.models.Route
import generated.com.ifmo.se.route.dto.GetRoutesFilterParameterDto
import generated.com.ifmo.se.route.dto.SortFieldsDto
import jakarta.ejb.Startup
import jakarta.ejb.Stateless
import jakarta.inject.Inject

@Stateless(name = "RouteManagementServiceBean")
@Startup
open class RouteManagementServiceImpl @Inject constructor(
    private val routeManagementClientImpl: RouteManagementClientInterface,
) : RouteManagementService {

    override fun addRoute(route: Route): EnrichedRoute =
        toDomain(routeManagementClientImpl.addRoute(toRouteUpsertRequestDto(route)))

    override fun getRoutes(
        limit: Int?,
        offset: Int?,
        sortBy: List<SortFieldsDto>?,
        filter: GetRoutesFilterParameterDto?
    ): List<EnrichedRoute>? =
        routeManagementClientImpl.getRoutes(filter, sortBy, limit, offset).routes?.map { toDomain(it) }
}