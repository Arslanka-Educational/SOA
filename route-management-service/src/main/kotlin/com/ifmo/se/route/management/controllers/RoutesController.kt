package org.example.com.ifmo.se.route.management.controllers

import generated.com.ifmo.se.route.dto.*
import jakarta.jws.WebMethod
import jakarta.jws.WebParam
import jakarta.jws.WebService
import lombok.RequiredArgsConstructor
import mu.KLogging
import org.example.com.ifmo.se.route.management.services.RouteService
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import java.math.BigDecimal

@RequiredArgsConstructor
@WebService(serviceName = "RoutesController")
@Controller
open class RoutesController(
    private val routeService: RouteService,
) {
    private companion object : KLogging()

    @WebMethod(operationName = "deleteRouteByDistance")
    fun deleteRouteByDistance(
        @WebParam(name = "distance") distance: Double
    ): ResponseEntity<RouteDto> {
        return ResponseEntity.ok(routeService.deleteRouteByDistance(distance = distance))
    }

    @WebMethod(operationName = "deleteRouteById")
    fun deleteRouteById(
        @WebParam(name = "id") id: Int,
        @WebParam(name = "distance") distance: BigDecimal?
    ): ResponseEntity<RouteDto> {
        return ResponseEntity.ok(routeService.deleteRouteById(id))
    }

    @WebMethod(operationName = "getRouteById")
    fun getRouteById(
        @WebParam(name = "id") id: Int
    ): ResponseEntity<RouteDto> {
        return ResponseEntity.ok(routeService.getById(id))
    }

    @WebMethod(operationName = "getRoutes")
    fun getRoutes(
        @WebParam(name = "getRoutesFilterParameterDto") getRoutesFilterParameterDto: GetRoutesFilterParameterDto,
        @WebParam(name = "limit") limit: Int?,
        @WebParam(name = "offset") offset: Int?,
        @WebParam(name = "sortBy") sortBy: List<SortFieldsDto>?
    ): ResponseEntity<RouteResponseDto> {
        logger.info {
            """
            limit: $limit
            offset: $offset
            sortBy: $sortBy
            filter: $getRoutesFilterParameterDto
        """.trimIndent()
        }
        return ResponseEntity.ok(
            routeService.getPaginatedFilteredRoutes(
                getRoutesFilterParameterDto,
                offset,
                limit,
                sortBy
            )
        )

    }

    @WebMethod(operationName = "getRoutesCounts")
    fun getRoutesCounts(
        @WebParam(name = "maxDistance") maxDistance: Double?
    ): ResponseEntity<Int> {
        return ResponseEntity.ok(routeService.getRoutesCountByDistance(maxDistance))
    }

    @WebMethod(operationName = "postRoute")
    fun postRoute(
        @WebParam(name = "routeUpsertRequestDto") routeUpsertRequestDto: RouteUpsertRequestDto
    ): ResponseEntity<RouteDto> {
        return ResponseEntity.ok(routeService.save(routeUpsertRequestDto))
    }

    @WebMethod(operationName = "updateRouteById")
    fun updateRouteById(
        @WebParam(name = "id") id: Int,
        @WebParam(name = "routeUpsertRequestDto") routeUpsertRequestDto: RouteUpsertRequestDto
    ): ResponseEntity<RouteDto> {
        return ResponseEntity.ok(routeService.updateRoute(id, routeUpsertRequestDto))
    }
}