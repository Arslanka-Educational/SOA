package org.example.com.ifmo.se.route.management.data.repositories

import org.example.com.ifmo.se.route.management.data.models.Route
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface RouteRepository : JpaRepository<Route, Long>, CustomRouteRepository {
    @Query("SELECT COUNT(r) FROM Route r WHERE (:maxDistance IS NULL OR r.distance < :maxDistance)")
    fun countRoutesWithDistanceLessThan(@Param("maxDistance") maxDistance: Double?): Long

    @Query(value = "SELECT * FROM route WHERE distance = :distance LIMIT 1", nativeQuery = true)
    fun findOneByDistance(@Param("distance") distance: Double): Route?
}
