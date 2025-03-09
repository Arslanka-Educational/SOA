package com.ifmo.se.navigator.ejb.services

import RouteManagementClientImpl
import com.ifmo.se.navigator.dtos.GetRoutesFilterParameterDto
import com.ifmo.se.navigator.dtos.SortFieldsDto
import com.ifmo.se.navigator.ejb.mappers.toDomain
import com.ifmo.se.navigator.ejb.mappers.toRouteUpsertRequestDto
import com.ifmo.se.navigator.models.EnrichedRoute
import com.ifmo.se.navigator.models.Route
import jakarta.ejb.Stateless
import org.jboss.ejb3.annotation.Pool

@Stateless
@Pool("routes-pool")
open class RouteManagementServiceImpl : RouteManagementService {

    private val routeManagementClientImpl = RouteManagementClientImpl()

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