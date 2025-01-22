package org.example.com.ifmo.se.route.management.data.models

import jakarta.persistence.*
import jakarta.validation.constraints.DecimalMax
import jakarta.validation.constraints.DecimalMin

@Entity
data class Route(
    @Column(nullable = false)
    var name: String,

    @Embedded
    var coordinates: Coordinates,

    @field:DecimalMin("1")
    @Column(nullable = false)
    var distance: Double,

    @ManyToOne
    var from: Location? = null,

    @ManyToOne
    var to: Location? = null
) : BaseEntity()

@Embeddable
data class Coordinates(
    val x: Int,

    @field:DecimalMax("610")
    val y: Double
)