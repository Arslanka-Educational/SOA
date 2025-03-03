package org.example.com.ifmo.se.route.management.controllers

import generated.com.ifmo.se.route.api.RoutesApi
import generated.com.ifmo.se.route.dto.*
import lombok.RequiredArgsConstructor
import mu.KLogging
import org.example.com.ifmo.se.route.management.services.RouteService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController
import java.math.BigDecimal

@RequiredArgsConstructor
@RestController
open class RoutesController(
    private val routeService: RouteService,
) : RoutesApi {
    private companion object : KLogging()

    override fun deleteRouteByDistance(distance: Double): ResponseEntity<RouteDto> {
        return ResponseEntity.ok(routeService.deleteRouteByDistance(distance = distance))
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
        getRoutesFilterParameter: GetRoutesFilterParameterDto?
    ): ResponseEntity<RouteResponseDto> {
        logger.info {
            """
            limit: $limit
            offset: $offset
            sortBy: $sortBy
            filter: $getRoutesFilterParameter
        """.trimIndent()
        }
        return ResponseEntity.ok(routeService.getPaginatedFilteredRoutes(getRoutesFilterParameter, offset, limit, sortBy))
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