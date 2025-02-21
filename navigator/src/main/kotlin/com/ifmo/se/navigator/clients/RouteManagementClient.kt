package com.ifmo.se.navigator.com.ifmo.se.navigator.clients

import com.ifmo.se.navigator.com.ifmo.se.navigator.configs.FeignClientConfig
import feign.Param
import feign.RequestLine
import generated.com.ifmo.se.route.dto.*
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.ResponseEntity


@FeignClient(
    name = "routeManagementClient",
    url = "\${client.route-management.server.url}",
    configuration = [FeignClientConfig::class]
)
interface RouteManagementClient {

    @RequestLine("POST /routes")
    fun postRoute(
        request: RouteUpsertRequestDto,
    ): ResponseEntity<RouteDto>

    @RequestLine("GET /locations/{id}")
    fun getLocationById(
        @Param("id") id: Long,
    ): ResponseEntity<LocationDto>

    @RequestLine("GET /routes?filter={filter}&sortBy={sortBy}&limit={limit}&offset={offset}")
    fun getRoutes(
        @Param("filter") filter: GetRoutesFilterParameterDto?,
        @Param("sortBy") sortBy: List<SortFieldsDto>?,
        @Param("limit") limit: Int?,
        @Param("offset") offset: Int?
    ): ResponseEntity<RouteResponseDto>

}