package com.ifmo.se.navigator.services

import mu.KLogging
import org.springframework.stereotype.Service
import com.ifmo.se.navigator.mappers.toDomain
import com.ifmo.se.navigator.models.Location
import com.ifmo.se.navigator.models.LocationId
import generated.com.ifmo.se.route.api.LocationsApi

@Service
class LocationManagementService(
    private val locationsApi: LocationsApi,
) {
    private companion object : KLogging()

    internal suspend fun getLocationById(locationId: LocationId): Location =
        locationsApi.getLocationById(id = locationId.id).body().toDomain().also {
            logger.info { "get by id=$locationId location=$it" }
        }
}