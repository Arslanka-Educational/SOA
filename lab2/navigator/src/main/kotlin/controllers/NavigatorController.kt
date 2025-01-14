package ru.openbook.controllers

import kotlinx.coroutines.runBlocking
import org.springframework.http.ResponseEntity
import ru.navigator.api.NavigatorApi
import ru.openbook.mappers.toNavigatorRouteDto
import ru.openbook.models.LocationId
import ru.openbook.services.NavigatorService
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