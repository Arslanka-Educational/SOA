package org.example.com.ifmo.se.route.management.data.models

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import java.time.Instant

@MappedSuperclass
open class BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0L

    @CreationTimestamp
    val creationDate: Instant = Instant.now()
}