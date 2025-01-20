package org.example.com.ifmo.se.route.management.data.models

import jakarta.persistence.Entity

@Entity
class Location(
    val x: Int,
    val z: Long,
    val y: Int? = null,
    val name: String? = null,
) : BaseEntity()
