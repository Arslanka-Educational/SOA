package org.example.com.ifmo.se.route.management.services.impl

import generated.com.ifmo.se.route.dto.LocationDto
import jakarta.persistence.EntityNotFoundException
import lombok.RequiredArgsConstructor
import mu.KLogging
import org.example.com.ifmo.se.route.management.data.mappers.LocationMapper
import org.example.com.ifmo.se.route.management.data.repositories.LocationRepository
import org.example.com.ifmo.se.route.management.services.LocationService
import org.springframework.stereotype.Service

@Service
@RequiredArgsConstructor
class LocationServiceImpl(
    private val locationRepository: LocationRepository,
    private val locationMapper: LocationMapper,
) : LocationService {
    private companion object : KLogging()

    override fun getLocationById(locationId: Long): LocationDto {
        val location = locationRepository.findById(locationId).orElseThrow {
            EntityNotFoundException("Location with ID $locationId not found")
        }
        return locationMapper.map(location)
    }

}