package org.example.com.ifmo.se.route.management.data.repositories

import generated.com.ifmo.se.route.dto.GetRoutesFilterParameterDto
import generated.com.ifmo.se.route.dto.SortFieldsDto
import org.example.com.ifmo.se.route.management.data.models.Route

interface CustomRouteRepository {
    fun findRoutesWithFiltersAndSort(
        filter: GetRoutesFilterParameterDto?,
        offset: Int?,
        limit: Int?,
        sortBy: List<SortFieldsDto>?
    ): List<Route>

}