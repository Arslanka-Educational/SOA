package com.ifmo.se.navigator.ejb.services

import com.ifmo.se.navigator.models.EnrichedRoute
import com.ifmo.se.navigator.models.Route
import generated.com.ifmo.se.route.dto.GetRoutesFilterParameterDto
import generated.com.ifmo.se.route.dto.SortFieldsDto
import jakarta.ejb.Remote

@Remote
interface RouteManagementService {
    fun addRoute(route: Route): EnrichedRoute
    fun getRoutes(
        limit: Int?,
        offset: Int?,
        sortBy: List<SortFieldsDto>?,
        filter: GetRoutesFilterParameterDto?
    ): List<EnrichedRoute>?
}