package com.ifmo.se.navigator.services

import com.ifmo.se.navigator.com.ifmo.se.navigator.clients.RouteManagementClient
import com.ifmo.se.navigator.com.ifmo.se.navigator.exceptions.EntityNotFoundException
import com.ifmo.se.navigator.mappers.toDomain
import com.ifmo.se.navigator.models.Location
import com.ifmo.se.navigator.models.LocationId
import feign.FeignException
import mu.KLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


@Service

class LocationManagementService(
    @Autowired
    private val routeManagementClient: RouteManagementClient,
) {
    private companion object : KLogging()

    internal suspend fun getLocationById(locationId: LocationId): Location? =
        try {
            routeManagementClient.getLocationById(id = locationId.id).body?.toDomain().also {
                logger.info { "get by id=$locationId location=$it" }
            }
        } catch (ex: FeignException.NotFound) {
            throw EntityNotFoundException("Location with ID $locationId not found")
        }
}