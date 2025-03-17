package com.ifmo.se.navigator.controllers

import com.ifmo.se.navigator.ejb.services.NavigatorService
import com.ifmo.se.navigator.mappers.toNavigatorRouteDto
import com.ifmo.se.navigator.mappers.toRoutesResponse
import com.ifmo.se.navigator.models.Coordinate
import com.ifmo.se.navigator.models.LocationId
import generated.com.ifmo.se.navigator.api.NavigatorApi
import generated.com.ifmo.se.navigator.dto.RouteAddRequestDto
import generated.com.ifmo.se.navigator.dto.RouteDto
import generated.com.ifmo.se.navigator.dto.RouteResponseDto
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
open class NavigatorController(
    private val navigatorServiceImpl: NavigatorService,
) : NavigatorApi {
    override fun addRoute(
        idFrom: Long,
        idTo: Long,
        distance: Double,
        routeAddRequestDto: RouteAddRequestDto,
    ): ResponseEntity<RouteDto> =
        with(routeAddRequestDto) {
            navigatorServiceImpl.addRoute(
                idFrom = LocationId(idFrom),
                idTo = LocationId(idTo),
                distance = distance,
                coordinate = coordinates?.let { Coordinate(x = it.x, y = it.y) },
                name = name,
            )
        }.let {
            ResponseEntity.ok(it.toNavigatorRouteDto())
        }

    override fun getRoute(idFrom: Long, idTo: Long, shortest: Boolean): ResponseEntity<RouteResponseDto> =
        navigatorServiceImpl.getRoutesBetweenLocations(
            idFrom = LocationId(idFrom),
            idTo = LocationId(idTo),
            shortest = shortest,
        ).let {
            ResponseEntity.ok(it?.toRoutesResponse())
        }
}