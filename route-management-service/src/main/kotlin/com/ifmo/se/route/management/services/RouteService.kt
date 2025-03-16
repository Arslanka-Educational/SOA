package org.example.com.ifmo.se.route.management.services

import com.ifmo.se.route.management.wsdl.GetRoutesFilterParameterDto
import com.ifmo.se.route.management.wsdl.RouteDto
import com.ifmo.se.route.management.wsdl.RouteResponseDto
import com.ifmo.se.route.management.wsdl.RouteUpsertRequestDto
import org.example.com.ifmo.se.route.management.data.models.SortFieldsDto


interface RouteService {
    fun getPaginatedFilteredRoutes(
        filter: GetRoutesFilterParameterDto?,
        offset: Int?,
        limit: Int?,
        sortBy: List<SortFieldsDto>?,
    ): RouteResponseDto

    fun save(routeDto: RouteUpsertRequestDto): RouteDto

    fun updateRoute(routeId: Int, routeDto: RouteUpsertRequestDto): RouteDto

    fun getById(routeId: Int): RouteDto

    fun deleteRouteById(routeId: Int): RouteDto

    fun deleteRouteByDistance(distance: Double): RouteDto

    fun getRoutesCountByDistance(maxDistance: Double?): Int
}