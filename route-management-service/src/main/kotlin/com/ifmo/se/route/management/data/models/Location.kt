package org.example.com.ifmo.se.route.management.data.models

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class Location(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    var x: Int,
    var z: Long,
    var y: Int? = null,
    var name: String? = null,
)
