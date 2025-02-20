package com.ifmo.se.navigator.services

import com.ifmo.se.navigator.com.ifmo.se.navigator.clients.RouteManagementClient
import mu.KLogging
import org.springframework.stereotype.Service
import com.ifmo.se.navigator.mappers.toDomain
import com.ifmo.se.navigator.models.Location
import com.ifmo.se.navigator.models.LocationId
import org.springframework.beans.factory.annotation.Autowired


@Service

class LocationManagementService(
    @Autowired
    private val routeManagementClient: RouteManagementClient,
) {
    private companion object : KLogging()

    internal suspend fun getLocationById(locationId: LocationId): Location? =
        routeManagementClient.getLocationById(id=locationId.id).body?.toDomain().also {
            logger.info { "get by id=$locationId location=$it" }
        }
//        locationsApi.getLocationById(id = locationId.id).body().toDomain().also {
//            logger.info { "get by id=$locationId location=$it" }
//        }
}