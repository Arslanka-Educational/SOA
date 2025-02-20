package org.example.com.ifmo.se.route.management.data.models

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id

@Entity
class Location(
    @Id
    @GeneratedValue
    val id: Long,
    val x: Int,
    val z: Long,
    val y: Int? = null,
    val name: String? = null,
)
