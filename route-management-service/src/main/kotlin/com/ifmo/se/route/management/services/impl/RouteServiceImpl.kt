package org.example.com.ifmo.se.route.management.services.impl

import com.ifmo.se.route.management.wsdl.GetRoutesFilterParameterDto
import com.ifmo.se.route.management.wsdl.RouteDto
import com.ifmo.se.route.management.wsdl.RouteResponseDto
import com.ifmo.se.route.management.wsdl.RouteUpsertRequestDto
import jakarta.persistence.EntityManager
import jakarta.persistence.EntityNotFoundException
import lombok.RequiredArgsConstructor
import mu.KLogging
import org.example.com.ifmo.se.route.management.data.mappers.mapToDto
import org.example.com.ifmo.se.route.management.data.mappers.mapToEntity
import org.example.com.ifmo.se.route.management.data.mappers.xmlGregorianCalendarToOffsetDateTime
import org.example.com.ifmo.se.route.management.data.models.Coordinates
import org.example.com.ifmo.se.route.management.data.models.Location
import org.example.com.ifmo.se.route.management.data.models.Route
import org.example.com.ifmo.se.route.management.data.models.SortFieldsDto
import org.example.com.ifmo.se.route.management.data.repositories.LocationRepository
import org.example.com.ifmo.se.route.management.data.repositories.RouteRepository
import org.example.com.ifmo.se.route.management.services.RouteService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
@RequiredArgsConstructor
open class RouteServiceImpl(
    private val routeRepository: RouteRepository,
    private val locationRepository: LocationRepository,
    private val entityManager: EntityManager,
) : RouteService {
    private companion object : KLogging()

    @Transactional
    override fun getPaginatedFilteredRoutes(
        filter: GetRoutesFilterParameterDto?,
        offset: Int?,
        limit: Int?,
        sortBy: List<SortFieldsDto>?,
    ): RouteResponseDto {
        val routes = routeRepository.findRoutesWithFiltersAndSort(
            filter = filter,
            offset = offset,
            limit = limit,
            sortBy = sortBy
        )
        logger.info { routes.toString() }
        val total = routes.count()

        return RouteResponseDto().apply {
            this.total = total
            this.limit = limit ?: 10
            this.offset = offset ?: 0
        }.also {
            it.routes.addAll(routes.map { it.mapToDto() })
        }
    }

    @Transactional
    override fun save(routeDto: RouteUpsertRequestDto): RouteDto {
        val fromLocation = locationRepository.findById(routeDto.from!!.id.toLong())
            .orElseGet {
                // Create and save a new Location entity if it doesn't exist
                val newLocation = routeDto.from.mapToEntity()
                entityManager.merge(newLocation)
            }

        val toLocation = locationRepository.findById(routeDto.to!!.id.toLong())
            .orElseGet {
                // Create and save a new Location entity if it doesn't exist
                val newLocation = routeDto.to.mapToEntity()
                entityManager.merge(newLocation)
            }

        // Create the Route entity and associate it with the Location entities
        val route = Route(
            name = routeDto.name,
            coordinates = routeDto.coordinates!!.mapToEntity(),
            distance = routeDto.distance,
            from = fromLocation,
            to = toLocation
        )

        val savedRoute = entityManager.merge(route)
        return savedRoute.mapToDto()
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

        return updatedRoute.mapToDto()
    }

    override fun getById(routeId: Int): RouteDto {
        val route = routeRepository.findById(routeId.toLong()).orElseThrow {
            EntityNotFoundException("Route with ID $routeId not found")
        }
        return route.mapToDto()
    }

    @Transactional
    override fun deleteRouteById(routeId: Int): RouteDto {
        val route = routeRepository.findById(routeId.toLong()).orElseThrow {
            EntityNotFoundException("Route with ID $routeId not found")
        }

        routeRepository.delete(route)

        return route.mapToDto()
    }

    @Transactional
    override fun deleteRouteByDistance(distance: Double): RouteDto {
        val route = routeRepository.findOneByDistance(distance = distance)
            ?: throw EntityNotFoundException("Route with distance $distance not found")

        routeRepository.delete(route)

        return route.mapToDto()
    }

    override fun getRoutesCountByDistance(maxDistance: Double?): Int {
        return routeRepository.countRoutesWithDistanceLessThan(maxDistance).toInt()
    }
}