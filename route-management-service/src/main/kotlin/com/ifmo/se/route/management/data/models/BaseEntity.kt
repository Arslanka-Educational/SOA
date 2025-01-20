package org.example.com.ifmo.se.route.management.data.models

import jakarta.persistence.*

@MappedSuperclass
open class BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0L
}