package ru.openbook.mappers

import ru.openbook.models.Coordinate
import ru.route.dto.CoordinatesDto
import ru.navigator.dto.CoordinatesDto as NavigatorCoordinatesDto

internal fun Coordinate.toRouteCoordinatesDto() = CoordinatesDto(
    x = x,
    y = y,
)

internal fun Coordinate.toNavigatorCoordinatesDto() = NavigatorCoordinatesDto(
    x = x,
    y = y,
)
internal fun CoordinatesDto.toDomain() = Coordinate(x = this.x, y = this.y)