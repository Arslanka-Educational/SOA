package org.example.com.ifmo.se.route.management.data.repositories

import com.ifmo.se.route_management.GetRoutesFilterParameterDto
import org.example.com.ifmo.se.route.management.data.models.Route
import org.example.com.ifmo.se.route.management.data.models.SortFieldsDto

interface CustomRouteRepository {
    fun findRoutesWithFiltersAndSort(
        filter: GetRoutesFilterParameterDto?,
        offset: Int?,
        limit: Int?,
        sortBy: List<SortFieldsDto>?
    ): List<Route>

}