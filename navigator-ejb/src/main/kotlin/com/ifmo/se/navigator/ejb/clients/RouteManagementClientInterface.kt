package com.ifmo.se.navigator.ejb.clients

import generated.com.ifmo.se.route.dto.GetRoutesFilterParameterDto
import generated.com.ifmo.se.route.dto.LocationDto
import generated.com.ifmo.se.route.dto.LocationResponseDto
import generated.com.ifmo.se.route.dto.RouteDto
import generated.com.ifmo.se.route.dto.RouteResponseDto
import generated.com.ifmo.se.route.dto.RouteUpsertRequestDto
import generated.com.ifmo.se.route.dto.SortFieldsDto

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