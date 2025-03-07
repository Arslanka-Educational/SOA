package com.ifmo.se.navigator.ejb.services

import com.ifmo.se.navigator.models.Location
import com.ifmo.se.navigator.models.LocationId
import com.ifmo.se.navigator.models.LocationResponse
import jakarta.ejb.Remote

@Remote
interface LocationManagementServiceInterface {
    fun getLocationById(locationId: LocationId): Location?
    fun getLocations(limit: Int?, offset: Int?): LocationResponse?
}