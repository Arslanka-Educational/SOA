package org.example.com.ifmo.se.route.management.services

import generated.com.ifmo.se.route.dto.GetRoutesFilterParameterDto
import generated.com.ifmo.se.route.dto.RouteDto
import generated.com.ifmo.se.route.dto.RouteResponseDto
import generated.com.ifmo.se.route.dto.RouteUpsertRequestDto
import org.springframework.data.domain.Sort

interface RouteService {
    fun getPaginatedFilteredRoutes(
        filter: GetRoutesFilterParameterDto?,
        offset: Int?,
        limit: Int?,
        sortBy: List<String>?,
        sortDirection: Sort.Direction = Sort.Direction.ASC
    ): RouteResponseDto

    fun save(routeDto: RouteUpsertRequestDto): RouteDto

    fun updateRoute(routeId: Int, routeDto: RouteUpsertRequestDto): RouteDto

    fun getById(routeId: Int): RouteDto

    fun deleteRouteById(routeId: Int): RouteDto

    fun getRoutesCountByDistance(maxDistance: Double?): Int
}