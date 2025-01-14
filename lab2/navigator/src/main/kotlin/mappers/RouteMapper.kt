package ru.openbook.mappers

import ru.openbook.models.EnrichedRoute
import ru.openbook.models.Route
import ru.route.dto.RouteDto
import ru.navigator.dto.RouteDto as NavigatorRouteDto
import ru.route.dto.RouteUpsertRequestDto

internal fun Route.toRouteUpsertRequestDto() = RouteUpsertRequestDto(
    name = name,
    coordinates = coordinate?.toRouteCoordinatesDto(),
    distance = distance,
    from = locationFrom?.toRouteLocationDto(),
    to = locationTo?.toRouteLocationDto(),
)

internal fun RouteDto.toDomain() = EnrichedRoute(
    id = this.id,
    name = this.name,
    coordinate = this.coordinates?.toDomain(),
    locationFrom = this.from?.toDomain(),
    locationTo = this.to?.toDomain(),
    distance = this.distance,
    creationDate = this.creationDate,
)

internal fun EnrichedRoute.toRouteDto() = RouteDto(
    id = this.id,
    name = this.name,
    coordinates = this.coordinate?.toRouteCoordinatesDto(),
    from = this.locationFrom?.toRouteLocationDto(),
    to = this.locationTo?.toRouteLocationDto(),
    distance = this.distance,
    creationDate = this.creationDate,
)

internal fun EnrichedRoute.toNavigatorRouteDto() = NavigatorRouteDto(
    id = this.id,
    name = this.name,
    coordinates = this.coordinate?.toNavigatorCoordinatesDto(),
    from = this.locationFrom?.toNavigatorLocationDto(),
    to = this.locationTo?.toNavigatorLocationDto(),
    distance = this.distance,
    creationDate = this.creationDate,
)
