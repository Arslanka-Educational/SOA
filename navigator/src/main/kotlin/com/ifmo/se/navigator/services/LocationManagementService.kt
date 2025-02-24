package com.ifmo.se.navigator.services

import com.ifmo.se.navigator.com.ifmo.se.navigator.clients.RouteManagementClient
import com.ifmo.se.navigator.mappers.toDomain
import com.ifmo.se.navigator.models.Location
import com.ifmo.se.navigator.models.LocationId
import generated.com.ifmo.se.navigator.dto.LocationResponseDto
import mu.KLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


@Service
class LocationManagementService(
    @Autowired
    private val routeManagementClient: RouteManagementClient,
) {
    private companion object : KLogging()

    internal fun getLocationById(locationId: LocationId): Location? =
            routeManagementClient.getLocationById(id = locationId.id).body?.toDomain().also {
                logger.info { "get by id=$locationId location=$it" }
            }

    internal suspend fun getLocations(limit: Int?, offset: Int?): LocationResponseDto? =
        routeManagementClient.getLocations(
            limit = limit,
            offset = offset
        ).body.also {
            logger.info { "get locations = $it" }
        }

}