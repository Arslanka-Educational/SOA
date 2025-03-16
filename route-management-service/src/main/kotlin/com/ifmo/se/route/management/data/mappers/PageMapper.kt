package org.example.com.ifmo.se.route.management.data.mappers

import com.ifmo.se.route.management.wsdl.LocationResponseDto
import mapToDto
import org.example.com.ifmo.se.route.management.data.models.Location
import org.springframework.data.domain.Page

internal fun Page<Location>.toResponse() = LocationResponseDto().apply {
    this.total = totalElements.toInt()
}.also {
    it.locations.addAll(content.toList().map { it.mapToDto() })
}