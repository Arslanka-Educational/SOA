package com.ifmo.se.navigator.ejb.clients

import generated.com.ifmo.se.route.dto.*
import jakarta.ejb.Remote

@Remote
interface RouteManagementClientInterface {
    fun addRoute(request: RouteUpsertRequestDto): RouteDto
    fun getRoutes(
        filter: GetRoutesFilterParameterDto?,
        sortBy: List<SortFieldsDto>?,
        limit: Int?,
        offset: Int?
    ): RouteResponseDto

    fun getLocationById(id: Long): LocationDto
    fun getLocations(
        limit: Int?,
        offset: Int?
    ): LocationResponseDto
}