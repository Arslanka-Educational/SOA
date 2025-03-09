package com.ifmo.se.navigator.ejb.services

import com.ifmo.se.navigator.models.EnrichedRoute
import com.ifmo.se.navigator.models.Route
import com.ifmo.se.navigator.dtos.GetRoutesFilterParameterDto
import com.ifmo.se.navigator.dtos.SortFieldsDto
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