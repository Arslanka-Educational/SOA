package org.example.com.ifmo.se.route.management.controllers

import com.ifmo.se.route.management.wsdl.GetRoutesCountsRequest
import com.ifmo.se.route.management.wsdl.GetRoutesCountsResponse
import mu.KLogging
import org.example.com.ifmo.se.route.management.services.RouteService
import org.springframework.stereotype.Component
import org.springframework.ws.server.endpoint.annotation.Endpoint
import org.springframework.ws.server.endpoint.annotation.PayloadRoot
import org.springframework.ws.server.endpoint.annotation.RequestPayload
import org.springframework.ws.server.endpoint.annotation.ResponsePayload

@Endpoint
@Component
open class RoutesController(
    private val routeService: RouteService,
) {
    private companion object : KLogging()

//    @WebMethod(operationName = "deleteRouteByDistance")
//    fun deleteRouteByDistance(
//        @WebParam(name = "distance") distance: Double
//    ): RouteDto {
//        return routeService.deleteRouteByDistance(distance = distance)
//    }
//
//    @WebMethod(operationName = "deleteRouteById")
//    fun deleteRouteById(
//        @WebParam(name = "id") id: Int,
//    ): RouteDto {
//        return routeService.deleteRouteById(id)
//    }
//
//    @WebMethod(operationName = "getRouteById")
//    fun getRouteById(
//        @WebParam(name = "id") id: Int
//    ): RouteDto {
//        return routeService.getById(id)
//    }
//
//    @WebMethod(operationName = "getRoutes")
//    fun getRoutes(
//        @WebParam(name = "getRoutesFilterParameterDto") getRoutesFilterParameterDto: GetRoutesFilterParameterDto,
//        @WebParam(name = "limit") limit: Int?,
//        @WebParam(name = "offset") offset: Int?,
//        @WebParam(name = "sortBy") sortBy: List<SortFieldsDto>?
//    ): RouteResponseDto {
//        logger.info {
//            """
//            limit: $limit
//            offset: $offset
//            sortBy: $sortBy
//            filter: $getRoutesFilterParameterDto
//        """.trimIndent()
//        }
//        return routeService.getPaginatedFilteredRoutes(
//            getRoutesFilterParameterDto,
//            offset,
//            limit,
//            sortBy
//        )
//    }

    @PayloadRoot(namespace = "http://ifmo.com/se/route-management", localPart = "getRoutesCountsRequest")
    @ResponsePayload
    fun getRoutesCounts(
        @RequestPayload request: GetRoutesCountsRequest
    ): GetRoutesCountsResponse {
        logger.info { request.toString() }
        return GetRoutesCountsResponse().apply {
            this.count = routeService.getRoutesCountByDistance(request.maxDistance)
        }
    }

//    @WebMethod(operationName = "postRoute")
//    fun postRoute(
//        @WebParam(name = "routeUpsertRequestDto") routeUpsertRequestDto: RouteUpsertRequestDto
//    ): RouteDto {
//        return routeService.save(routeUpsertRequestDto)
//    }
//
//    @WebMethod(operationName = "updateRouteById")
//    fun updateRouteById(
//        @WebParam(name = "id") id: Int,
//        @WebParam(name = "routeUpsertRequestDto") routeUpsertRequestDto: RouteUpsertRequestDto
//    ): RouteDto {
//        return routeService.updateRoute(id, routeUpsertRequestDto)
//    }
}