package com.ifmo.se.navigator.ejb.services

import com.ifmo.se.navigator.models.Coordinate
import com.ifmo.se.navigator.models.EnrichedRoute
import com.ifmo.se.navigator.models.LocationId
import jakarta.ejb.Remote

@Remote
interface NavigatorService {
    fun addRoute(
        idFrom: LocationId,
        idTo: LocationId,
        distance: Double,
        name: String,
        coordinate: Coordinate?
    ): EnrichedRoute

    fun getRoutesBetweenLocations(
        idFrom: LocationId,
        idTo: LocationId,
        shortest: Boolean
    ): List<EnrichedRoute>?
}