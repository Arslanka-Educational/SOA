package org.example.com.ifmo.se.route.management.data.repositories

import org.example.com.ifmo.se.route.management.data.models.Location
import org.springframework.data.jpa.repository.JpaRepository

interface LocationRepository : JpaRepository<Location, Long> {
}