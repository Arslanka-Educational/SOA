package org.example.com.ifmo.se.route.management.services.impl

import generated.com.ifmo.se.route.dto.LocationDto
import generated.com.ifmo.se.route.dto.LocationResponseDto
import jakarta.persistence.EntityNotFoundException
import lombok.RequiredArgsConstructor
import mu.KLogging
import org.example.com.ifmo.se.route.management.data.mappers.LocationMapper
import org.example.com.ifmo.se.route.management.data.mappers.toResponse
import org.example.com.ifmo.se.route.management.data.models.Location
import org.example.com.ifmo.se.route.management.data.repositories.LocationRepository
import org.example.com.ifmo.se.route.management.services.LocationService
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

@Service
@RequiredArgsConstructor
open class LocationServiceImpl(
    private val locationRepository: LocationRepository,
    private val locationMapper: LocationMapper,
) : LocationService {
    private companion object : KLogging()

    override suspend fun getLocationById(locationId: Long): LocationDto {
        println("locations" + locationRepository.findAll())
        val location = locationRepository.findById(locationId).orElseThrow {
            EntityNotFoundException("Location with ID $locationId not found")
        }
        return locationMapper.map(location)
    }

    override suspend fun getLocations(
        limit: Int?,
        offset: Int?
    ): LocationResponseDto? {
        val page = offset?.let {
            limit?.let { offset / limit }
        } ?: 0
        val locations: Page<Location> = locationRepository.findAll(
            PageRequest.of(page, limit ?: 10)
        )

        return locations.toResponse(locationMapper)
    }

}