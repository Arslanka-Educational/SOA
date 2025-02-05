package com.ifmo.se.navigator.controllers

import com.ifmo.se.navigator.mappers.toNavigatorRouteDto
import com.ifmo.se.navigator.models.Coordinate
import com.ifmo.se.navigator.models.LocationId
import com.ifmo.se.navigator.services.NavigatorService
import generated.com.ifmo.se.navigator.api.NavigatorApi
import generated.com.ifmo.se.navigator.dto.RouteAddRequestDto
import generated.com.ifmo.se.navigator.dto.RouteDto
import io.swagger.v3.oas.annotations.Parameter
import kotlinx.coroutines.runBlocking
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid
import javax.validation.constraints.Min

@RestController
open class NavigatorController(
    private val navigatorService: NavigatorService,
) : NavigatorApi {
    override fun addRoute(
        idFrom: Long,
        idTo: Long,
        distance: Double,
        routeAddRequestDto: RouteAddRequestDto,
    ): ResponseEntity<RouteDto> = runBlocking {
        with(routeAddRequestDto) {
            navigatorService.addRoute(
                idFrom = LocationId(idFrom),
                idTo = LocationId(idTo),
                distance = distance,
                coordinate = coordinates?.let { Coordinate(x = it.x, y = it.y) },
                name = name,
            )
        }
    }.let {
        ResponseEntity.ok(it.toNavigatorRouteDto())
    }

    override fun getRoute(idFrom: Long, idTo: Long, shortest: Boolean): ResponseEntity<RouteDto> = runBlocking {
        navigatorService.getRouteBetweenLocations(
            idFrom = LocationId(idFrom),
            idTo = LocationId(idTo),
            shortest = shortest,
        ).let {
            ResponseEntity.ok(it?.toNavigatorRouteDto())
        }
    }
}