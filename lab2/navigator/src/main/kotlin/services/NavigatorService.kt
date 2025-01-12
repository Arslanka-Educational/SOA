package ru.openbook.services

import org.springframework.stereotype.Service

@Service
class NavigatorService(
    private val routeManagementService: RouteManagementService,
) {
}