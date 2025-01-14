package ru.openbook.services

import mu.KLogging
import org.springframework.stereotype.Service
import ru.openbook.mappers.toDomain
import ru.openbook.models.Location
import ru.openbook.models.LocationId
import ru.route.api.LocationsApi

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