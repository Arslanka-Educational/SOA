package org.example.com.ifmo.se.route.management.data.models

import jakarta.persistence.Entity
import jakarta.persistence.Id

@Entity
class Location(
    @Id
    val id: Long,
    var x: Int,
    var z: Long,
    var y: Int? = null,
    var name: String? = null,
)
