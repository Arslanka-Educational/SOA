package org.example.com.ifmo.se.route.management.controllers

import com.ifmo.se.route_management.DeleteRouteByDistanceRequest
import com.ifmo.se.route_management.DeleteRouteByDistanceResponse
import com.ifmo.se.route_management.DeleteRouteByIdRequest
import com.ifmo.se.route_management.DeleteRouteByIdResponse
import com.ifmo.se.route_management.GetRouteByIdRequest
import com.ifmo.se.route_management.GetRouteByIdResponse
import com.ifmo.se.route_management.GetRoutesCountsRequest
import com.ifmo.se.route_management.GetRoutesCountsResponse
import com.ifmo.se.route_management.GetRoutesRequest
import com.ifmo.se.route_management.GetRoutesRequestDto
import com.ifmo.se.route_management.GetRoutesResponseDto
import com.ifmo.se.route_management.PostRouteRequest
import com.ifmo.se.route_management.PostRouteResponse
import com.ifmo.se.route_management.UpdateRouteByIdRequest
import com.ifmo.se.route_management.UpdateRouteByIdResponse
import mu.KLogging
import org.example.com.ifmo.se.route.management.data.models.SortFieldsDto
import org.example.com.ifmo.se.route.management.services.RouteService
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Component
import org.springframework.ws.server.endpoint.annotation.Endpoint
import org.springframework.ws.server.endpoint.annotation.PayloadRoot
import org.springframework.ws.server.endpoint.annotation.RequestPayload
import org.springframework.ws.server.endpoint.annotation.ResponsePayload

@Endpoint
@Component
class RoutesController(
    private val routeService: RouteService,
) {
    private companion object : KLogging()

    @PayloadRoot(namespace = Namespace.NAMESPACE, localPart = "deleteRouteByDistance")
    @ResponsePayload
    fun deleteRouteByDistance(
        @RequestPayload request: DeleteRouteByDistanceRequest,
    ): DeleteRouteByDistanceResponse {
        return routeService.deleteRouteByDistance(distance = request.distance).let {
            DeleteRouteByDistanceResponse().apply {
                this.route = let@ it
            }
        }
    }

    @PayloadRoot(namespace = Namespace.NAMESPACE, localPart = "deleteRouteById")
    @ResponsePayload
    fun deleteRouteById(
        @RequestPayload request: DeleteRouteByIdRequest,
    ): DeleteRouteByIdResponse {
        return routeService.deleteRouteById(request.id).let {
            DeleteRouteByIdResponse().apply {
                this.route = let@ it
            }
        }
    }

    @PayloadRoot(namespace = Namespace.NAMESPACE, localPart = "getRouteById")
    @ResponsePayload
    fun getRouteById(
        @RequestPayload request: GetRouteByIdRequest,
    ): GetRouteByIdResponse {
        return routeService.getById(request.id).let {
            GetRouteByIdResponse().apply {
                this.route = let@ it
            }
        }
    }

    @PayloadRoot(namespace = Namespace.NAMESPACE, localPart = "getRoutes")
    @ResponsePayload
    fun getRoutes(
        @RequestPayload request: GetRoutesRequest,
    ): GetRoutesResponseDto {
        return routeService.getPaginatedFilteredRoutes(
            filter = request.filterParams,
            offset = request.offset,
            limit = request.limit,
            sortBy = request.sortBy.map { SortFieldsDto.valueOf(it) },
        ).let {
            GetRoutesResponseDto().apply {
                this.total = let@ total
            }.also {
                it.routes.addAll(let@ it.routes)
            }
        }
    }

    @PayloadRoot(namespace = Namespace.NAMESPACE, localPart = "getRoutesCountsRequest")
    @ResponsePayload
    fun getRoutesCounts(
        @RequestPayload request: GetRoutesCountsRequest
    ): GetRoutesCountsResponse {
        return GetRoutesCountsResponse().apply {
            this.count = routeService.getRoutesCountByDistance(request.maxDistance)
        }
    }

    @PayloadRoot(namespace = Namespace.NAMESPACE, localPart = "postRoute")
    @ResponsePayload
    fun postRoute(
        @RequestPayload request: PostRouteRequest
    ): PostRouteResponse {
        return routeService.save(request.route).let {
            PostRouteResponse().apply {
                this.route = let@ it
            }
        }
    }

    @PayloadRoot(namespace = Namespace.NAMESPACE, localPart = "updateRouteById")
    @ResponsePayload
    fun updateRouteById(
        @RequestPayload request: UpdateRouteByIdRequest,
    ): UpdateRouteByIdResponse {
        return routeService.updateRoute(routeId = request.id.toInt(), routeDto = request.route).let {
            UpdateRouteByIdResponse().apply {
                this.route = let@ it
            }
        }
    }
}