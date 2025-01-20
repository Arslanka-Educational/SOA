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

//    @AttributeOverrides(
//        AttributeOverride(name = "x", column = Column(name = "from_x")),
//        AttributeOverride(name = "z", column = Column(name = "from_z")),
//        AttributeOverride(name = "y", column = Column(name = "from_y")),
//        AttributeOverride(name = "name", column = Column(name = "from_name"))
//    )
    var from: Location? = null,

    @Embedded
    @AttributeOverrides(
        AttributeOverride(name = "x", column = Column(name = "to_x")),
        AttributeOverride(name = "z", column = Column(name = "to_z")),
        AttributeOverride(name = "y", column = Column(name = "to_y")),
        AttributeOverride(name = "name", column = Column(name = "to_name"))
    )
    var to: Location? = null
) : BaseEntity()

@Embeddable
data class Coordinates(
    val x: Int,

    @field:DecimalMax("610")
    val y: Double
)