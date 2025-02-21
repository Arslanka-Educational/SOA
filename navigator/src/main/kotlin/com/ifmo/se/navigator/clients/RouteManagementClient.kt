package com.ifmo.se.navigator.com.ifmo.se.navigator.clients

import generated.com.ifmo.se.route.dto.*
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.*
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate


@Service
open class RouteManagementClient(private val restTemplate: RestTemplate) {

    @Value("\${client.route-management.server.url}")
    private lateinit var baseUrl: String

    fun postRoute(request: RouteUpsertRequestDto): ResponseEntity<RouteDto> {
        val headers = HttpHeaders().apply {
            contentType = MediaType.APPLICATION_JSON
        }
        val entity = HttpEntity(request, headers)
        return restTemplate.exchange("$baseUrl/routes", HttpMethod.POST, entity, RouteDto::class.java)
    }

    fun getLocationById(id: Long): ResponseEntity<LocationDto> {
        return restTemplate.getForEntity("$baseUrl/locations/$id", LocationDto::class.java)
    }

    fun getRoutes(
        filter: GetRoutesFilterParameterDto?,
        sortBy: List<SortFieldsDto>?,
        limit: Int?,
        offset: Int?
    ): ResponseEntity<RouteResponseDto> {
        val params = mutableMapOf<String, Any>()
        filter?.let { params["filter"] = it }
        sortBy?.let { params["sortBy"] = it.joinToString(",") }
        limit?.let { params["limit"] = it }
        offset?.let { params["offset"] = it }

        return restTemplate.getForEntity(
            "$baseUrl/routes?filter={filter}&sortBy={sortBy}&limit={limit}&offset={offset}",
            RouteResponseDto::class.java,
            params
        )
    }
}