package ru.openbook.services

import org.springframework.stereotype.Service
import ru.route.api.RoutesApi

@Service
class RouteManagementService(
    private val routesApi: RoutesApi,
) {
}