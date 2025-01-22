package org.example.com.ifmo.se.route.management.data.repositories

import org.example.com.ifmo.se.route.management.data.models.Route
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface RouteRepository : JpaRepository<Route, Long>, JpaSpecificationExecutor<Route> {
    @Query("SELECT COUNT(r) FROM Route r WHERE (:maxDistance IS NULL OR r.distance < :maxDistance)")
    fun countRoutesWithDistanceLessThan(@Param("maxDistance") maxDistance: Double?): Long
}
