package org.example.com.ifmo.se.route.management.services.impl

import generated.com.ifmo.se.route.dto.*
import io.ktor.util.*
import jakarta.persistence.EntityManager
import jakarta.persistence.EntityNotFoundException
import lombok.RequiredArgsConstructor
import mu.KLogging
import org.example.com.ifmo.se.route.management.data.mappers.RouteMapper
import org.example.com.ifmo.se.route.management.data.models.Coordinates
import org.example.com.ifmo.se.route.management.data.models.Location
import org.example.com.ifmo.se.route.management.data.models.Route
import org.example.com.ifmo.se.route.management.data.repositories.LocationRepository
import org.example.com.ifmo.se.route.management.data.repositories.RouteRepository
import org.example.com.ifmo.se.route.management.data.repositories.specifications.RouteSpecification
import org.example.com.ifmo.se.route.management.services.RouteService
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@RequiredArgsConstructor
open class RouteServiceImpl(
    private val routeRepository: RouteRepository,
    private val locationRepository: LocationRepository,
    private val entityManager: EntityManager,
    private val routeMapper: RouteMapper,
) : RouteService {
    private companion object : KLogging()

    override fun getPaginatedFilteredRoutes(
        filter: GetRoutesFilterParameterDto?,
        offset: Int?,
        limit: Int?,
        sortBy: List<SortFieldsDto>?,
        sortDirection: Sort.Direction
    ): RouteResponseDto {
        val sort: Sort = if (!sortBy.isNullOrEmpty()) {
            Sort.by(sortBy.map { Sort.Order(sortDirection, it.value.toLowerCasePreservingASCIIRules()) })
        } else {
            Sort.unsorted()
        } //todo добавить фильтрацию по двум локациям

        val pageable: Pageable = PageRequest.of(offset ?: 0, limit ?: 10, sort)
        val specification = filter?.let { RouteSpecification(it) } ?: Specification.where(null)

        val page = routeRepository.findAll(specification, pageable)

        return RouteResponseDto(
            routes = page.content.map { routeMapper.map(it) },
            total = page.totalElements.toInt(),
            limit = limit ?: 10,
            offset = offset ?: 0
        )
    }

    @Transactional
    override fun save(routeDto: RouteUpsertRequestDto): RouteDto {
        val fromLocation = locationRepository.findById(routeDto.from!!.id!!.toLong())
            .orElseGet {
                // Create and save a new Location entity if it doesn't exist
                val newLocation = routeMapper.map(routeDto.from)
                entityManager.merge(newLocation)
            }

        val toLocation = locationRepository.findById(routeDto.to!!.id!!.toLong())
            .orElseGet {
                // Create and save a new Location entity if it doesn't exist
                val newLocation = routeMapper.map(routeDto.to)
                entityManager.merge(newLocation)
            }

        // Create the Route entity and associate it with the Location entities
        val route = Route(
            name = routeDto.name,
            coordinates = routeMapper.map(routeDto.coordinates!!),
            distance = routeDto.distance,
            from = fromLocation,
            to = toLocation
        )

        val savedRoute =  entityManager.merge(route)
        return routeMapper.map(savedRoute).also { it.creationDate.toOffsetTime() }
    }

    @Transactional
    override fun updateRoute(routeId: Int, routeDto: RouteUpsertRequestDto): RouteDto {
        val existingRoute = routeRepository.findById(routeId.toLong()).orElseThrow {
            EntityNotFoundException("Route with ID $routeId not found")
        }

        existingRoute.name = routeDto.name
        existingRoute.coordinates = routeDto.coordinates?.let {
            Coordinates(x = it.x, y = it.y)
        } ?: existingRoute.coordinates

        existingRoute.distance = routeDto.distance

        existingRoute.from = routeDto.from?.let {
            Location(id = existingRoute.from!!.id, x = it.x, y = it.y, z = it.z, name = it.name)
        } ?: existingRoute.from

        existingRoute.to = routeDto.to?.let {
            Location(id = existingRoute.to!!.id, x = it.x, y = it.y, z = it.z, name = it.name)
        } ?: existingRoute.to

        val updatedRoute = routeRepository.save(existingRoute)

        return routeMapper.map(updatedRoute)
    }

    override fun getById(routeId: Int): RouteDto {
        val route = routeRepository.findById(routeId.toLong()).orElseThrow {
            EntityNotFoundException("Route with ID $routeId not found")
        }
        return routeMapper.map(route)
    }

    @Transactional
    override fun deleteRouteById(routeId: Int): RouteDto {
        val route = routeRepository.findById(routeId.toLong()).orElseThrow {
            EntityNotFoundException("Route with ID $routeId not found")
        }

        routeRepository.delete(route)

        return routeMapper.map(route)
    }

    @Transactional
    override fun deleteRouteByDistance(distance: Double): RouteDto {
        val route = routeRepository.findOneByDistance(distance = distance)
            ?: throw EntityNotFoundException("Route with distance $distance not found")

        routeRepository.delete(route)

        return routeMapper.map(route)
    }

    override fun getRoutesCountByDistance(maxDistance: Double?): Int {
        return routeRepository.countRoutesWithDistanceLessThan(maxDistance).toInt()
    }
}