package com.ifmo.se.navigator.com.ifmo.se.navigator.clients

import com.ifmo.se.navigator.models.EnrichedRoute
import generated.com.ifmo.se.route.dto.RouteDto
import generated.com.ifmo.se.route.dto.LocationDto
import generated.com.ifmo.se.route.dto.GetRoutesFilterParameterDto
import generated.com.ifmo.se.route.dto.RouteResponseDto
import generated.com.ifmo.se.route.dto.RouteUpsertRequestDto
import generated.com.ifmo.se.route.dto.SortFieldsDto
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@FeignClient(name = "routeManagementClient", url = "\${client.route-management.server.url}")
interface RouteManagementClient {
    @PostMapping("/route")
    fun postRoute(
        @RequestBody request: RouteUpsertRequestDto,
    ): ResponseEntity<RouteDto>

    @GetMapping("/locations/{id}")
    fun getLocationById(
        @PathVariable id: Long,
    ): ResponseEntity<LocationDto>

    fun getRoutes(
        filter: GetRoutesFilterParameterDto?,
        sortBy: List<SortFieldsDto>?,
        limit: Int?,
        offset: Int?
    ): ResponseEntity<RouteResponseDto>

}