package com.ifmo.se.navigator.controllers

import com.ifmo.se.navigator.mappers.toNavigatorRouteDto
import com.ifmo.se.navigator.models.LocationId
import com.ifmo.se.navigator.services.NavigatorService
import generated.com.ifmo.se.navigator.api.NavigatorApi
import kotlinx.coroutines.runBlocking
import org.springframework.http.ResponseEntity
import java.util.UUID

class NavigatorController(
    private val navigatorService: NavigatorService,
) : NavigatorApi {
    override fun addRoute(idFrom: Long, idTo: Long, distance: Double) = runBlocking {
        navigatorService.addRoute(
            idFrom = LocationId(idFrom),
            idTo = LocationId(idTo),
            distance = distance,
            name = UUID.randomUUID().toString(),
        )
    }.let {
        ResponseEntity.ok(it.toNavigatorRouteDto())
    }
}