package com.ifmo.se.navigator.ejb.clients

import com.ifmo.se.navigator.dtos.*

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