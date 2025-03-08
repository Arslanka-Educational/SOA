package com.ifmo.se.navigator.ejb.services

import RouteManagementClientImpl
import com.ifmo.se.navigator.ejb.mappers.toDomain
import com.ifmo.se.navigator.ejb.mappers.toRouteUpsertRequestDto
import com.ifmo.se.navigator.models.EnrichedRoute
import com.ifmo.se.navigator.models.Route
import generated.com.ifmo.se.route.dto.GetRoutesFilterParameterDto
import generated.com.ifmo.se.route.dto.SortFieldsDto
import jakarta.ejb.Startup
import jakarta.ejb.Stateless
import jakarta.inject.Inject

@Stateless
@Startup
open class RouteManagementServiceImpl {

    @Inject
    private lateinit var routeManagementClientImpl: RouteManagementClientImpl

    internal fun addRoute(route: Route): EnrichedRoute =
        toDomain(routeManagementClientImpl.addRoute(toRouteUpsertRequestDto(route)))

    internal fun getRoutes(
        limit: Int?,
        offset: Int?,
        sortBy: List<SortFieldsDto>?,
        filter: GetRoutesFilterParameterDto?
    ): List<EnrichedRoute>? =
        routeManagementClientImpl.getRoutes(filter, sortBy, limit, offset).routes?.map { toDomain(it) }
}