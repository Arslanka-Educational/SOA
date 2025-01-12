package ru.openbook.controllers

import org.springframework.http.ResponseEntity
import ru.navigator.api.NavigatorApi
import ru.navigator.dto.CoordinatesDto
import ru.navigator.dto.RouteDto
import ru.navigator.dto.RouteResponseDto
import ru.openbook.services.NavigatorService
import java.time.OffsetDateTime

class NavigatorController(
    private val navigatorService: NavigatorService,
) : NavigatorApi {
    override fun addRoute(idFrom: Long, idTo: Long, distance: Double): ResponseEntity<RouteDto> {
        return RouteDto(
            id = 1L,
            name = "",
            coordinates = CoordinatesDto(1, .0),
            creationDate = OffsetDateTime.MAX,
        ).let {
            ResponseEntity.ok(it)
        }
    }

    override fun getRoute(idFrom: Long, idTo: Long, shortest: Boolean): ResponseEntity<RouteResponseDto> {
        return RouteResponseDto().let { ResponseEntity.ok(it) }
    }
}