package org.example.com.ifmo.se.route.management.controllers

import generated.com.ifmo.se.route.api.RoutesApi
import generated.com.ifmo.se.route.dto.GetRoutesFilterParameterDto
import generated.com.ifmo.se.route.dto.RouteDto
import generated.com.ifmo.se.route.dto.RouteResponseDto
import generated.com.ifmo.se.route.dto.RouteUpsertRequestDto
import generated.com.ifmo.se.route.dto.SortFieldsDto
import lombok.RequiredArgsConstructor
import org.example.com.ifmo.se.route.management.services.RouteService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController
import java.math.BigDecimal

@RequiredArgsConstructor
@RestController
open class RoutesController(
    private val routeService: RouteService,
) : RoutesApi {
    override fun deleteRouteByDistance(distance: Double): ResponseEntity<RouteDto> {
        return super.deleteRouteByDistance(distance)
    }

    override fun deleteRouteById(id: Int, distance: BigDecimal?): ResponseEntity<RouteDto> {
        return ResponseEntity.ok(routeService.deleteRouteById(id))
    }

    override fun getRouteById(id: Int): ResponseEntity<RouteDto> {
        return ResponseEntity.ok(routeService.getById(id))
    }

    override fun getRoutes(
        limit: Int?,
        offset: Int?,
        sortBy: List<SortFieldsDto>?,
        filter: GetRoutesFilterParameterDto?
    ): ResponseEntity<RouteResponseDto> {
        return ResponseEntity.ok(routeService.getPaginatedFilteredRoutes(filter, offset, limit, sortBy))
    }

    override fun getRoutesCounts(maxDistance: Double?): ResponseEntity<Int> {
        return ResponseEntity.ok(routeService.getRoutesCountByDistance(maxDistance))
    }

    override fun postRoute(routeUpsertRequestDto: RouteUpsertRequestDto): ResponseEntity<RouteDto> {
        return ResponseEntity.ok(routeService.save(routeUpsertRequestDto))
    }

    override fun updateRouteById(id: Int, routeUpsertRequestDto: RouteUpsertRequestDto): ResponseEntity<RouteDto> {
        return ResponseEntity.ok(routeService.updateRoute(id, routeUpsertRequestDto))
    }
}