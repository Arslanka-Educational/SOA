package org.example.com.ifmo.se.route.management.data.repositories.impl

import generated.com.ifmo.se.route.dto.GetRoutesFilterParameterDto
import generated.com.ifmo.se.route.dto.SortFieldsDto
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import jakarta.persistence.TypedQuery
import mu.KLogging
import org.example.com.ifmo.se.route.management.data.models.Route
import org.example.com.ifmo.se.route.management.data.repositories.CustomRouteRepository
import org.springframework.stereotype.Repository


@Repository
open class CustomRouteRepositoryImpl : CustomRouteRepository {
    private companion object : KLogging()

    @PersistenceContext
    lateinit var entityManager: EntityManager

    override fun findRoutesWithFiltersAndSort(
        filter: GetRoutesFilterParameterDto?,
        offset: Int?,
        limit: Int?,
        sortBy: List<SortFieldsDto>?
    ): List<Route> {
        val queryBuilder = StringBuilder("SELECT r FROM Route r WHERE 1=1")
        addFiltersToQueryBuilder(queryBuilder, filter)
        addSortingToQueryBuilder(queryBuilder, sortBy)
        queryBuilder.append(" LIMIT :limit OFFSET :offset")
        logger.info { queryBuilder.toString() }

        val query = entityManager.createQuery(queryBuilder.toString(), Route::class.java)
        setParametersForQuery(query, filter)
        query.setParameter("limit", limit ?: 10)
        query.setParameter("offset", offset ?: 0)

        query.parameters.forEach {
            logger.info { "param: ${it.name}" }
        }

        return query.resultList
    }
//
//    override fun countRoutesWithFilters(filter: GetRoutesFilterParameterDto?): Int {
//        val countQueryBuilder = StringBuilder("SELECT COUNT(r) FROM Route r WHERE 1=1")
//        addFiltersToQueryBuilder(countQueryBuilder, filter)
//
//        val countQuery = entityManager.createQuery(countQueryBuilder.toString(), Int::class.java)
//        setParametersForQuery(countQuery, filter)
//
//        return countQuery.singleResult ?: 0
//    }

    private fun addFiltersToQueryBuilder(queryBuilder: StringBuilder, filter: GetRoutesFilterParameterDto?) {
        if (filter == null) return

        if (filter.locationIdFrom != null) {
            queryBuilder.append(" AND r.from.id = :locationIdFrom")
        }
        if (filter.locationIdTo != null) {
            queryBuilder.append(" AND r.to.id = :locationIdTo")
        }
        if (filter.minId != null) {
            queryBuilder.append(" AND r.id >= :minId")
        }
        if (filter.maxId != null) {
            queryBuilder.append(" AND r.id <= :maxId")
        }
        if (!filter.name.isNullOrBlank()) {
            queryBuilder.append(" AND r.name = :name")
        }
        if (filter.minX != null) {
            queryBuilder.append(" AND r.coordinates.x >= :minX")
        }
        if (filter.maxX != null) {
            queryBuilder.append(" AND r.coordinates.x <= :maxX")
        }
        if (filter.minY != null) {
            queryBuilder.append(" AND r.coordinates.y >= :minY")
        }
        if (filter.maxY != null) {
            queryBuilder.append(" AND r.coordinates.y <= :maxY")
        }
        if (filter.minCreationDate != null) {
            queryBuilder.append(" AND r.creationDate > :minCreationDate")
        }
        if (filter.maxCreationDate != null) {
            queryBuilder.append(" AND r.creationDate <= :maxCreationDate")
        }
        if (filter.fromLocationX != null) {
            queryBuilder.append(" AND r.from.x >= :fromLocationX")
        }
        if (filter.toLocationX != null) {
            queryBuilder.append(" AND r.to.x <= :toLocationX")
        }
        if (filter.fromLocationY != null) {
            queryBuilder.append(" AND r.from.y >= :fromLocationY")
        }
        if (filter.toLocationY != null) {
            queryBuilder.append(" AND r.to.y <= :toLocationY")
        }
        if (filter.fromLocationZ != null) {
            queryBuilder.append(" AND r.from.z = :fromLocationZ")
        }
        if (filter.toLocationZ != null) {
            queryBuilder.append(" AND r.to.z = :toLocationZ")
        }
        if (filter.distance != null) {
            queryBuilder.append(" AND r.distance = :distance")
        }
        if (!filter.nameStartsWithSubstr.isNullOrBlank()) {
            queryBuilder.append(" AND r.name LIKE :nameStartsWithSubstr")
        }
    }

    private fun addSortingToQueryBuilder(queryBuilder: StringBuilder, sortBy: List<SortFieldsDto>?) {
        if (sortBy.isNullOrEmpty()) return

        queryBuilder.append(" ORDER BY ")
        sortBy.forEachIndexed { index, field ->
            val direction = if (field.value.startsWith("-")) "DESC" else "ASC"
            val fieldName = when (field.value.replace("-", "")) {
                "Id" -> "r.id"
                "Name" -> "r.name"
                "Coordinates" -> "r.coordinates.x, r.coordinates.y"
                "CreationDate" -> "r.creationDate"
                "From" -> "r.from.x, r.from.y, r.from.z"
                "To" -> "r.to.x, r.to.y, r.to.z"
                "Distance" -> "r.distance"
                else -> ""
            }
            queryBuilder.append("$fieldName $direction")
            if (index < sortBy.size - 1) queryBuilder.append(", ")
        }
    }

    private fun setParametersForQuery(query: TypedQuery<*>, filter: GetRoutesFilterParameterDto?) {
        if (filter == null) return

        if (filter.locationIdFrom != null) {
            query.setParameter("locationIdFrom", filter.locationIdFrom)
        }
        if (filter.locationIdTo != null) {
            query.setParameter("locationIdTo", filter.locationIdTo)
        }
        if (filter.minId != null) {
            query.setParameter("minId", filter.minId)
        }
        if (filter.maxId != null) {
            query.setParameter("maxId", filter.maxId)
        }
        if (!filter.name.isNullOrBlank()) {
            query.setParameter("name", filter.name)
        }
        if (filter.minX != null) {
            query.setParameter("minX", filter.minX)
        }
        if (filter.maxX != null) {
            query.setParameter("maxX", filter.maxX)
        }
        if (filter.minY != null) {
            query.setParameter("minY", filter.minY)
        }
        if (filter.maxY != null) {
            query.setParameter("maxY", filter.maxY)
        }
        if (filter.minCreationDate != null) {
            query.setParameter("minCreationDate", filter.minCreationDate)
        }
        if (filter.maxCreationDate != null) {
            query.setParameter("maxCreationDate", filter.maxCreationDate)
        }
        if (filter.fromLocationX != null) {
            query.setParameter("fromLocationX", filter.fromLocationX)
        }
        if (filter.toLocationX != null) {
            query.setParameter("toLocationX", filter.toLocationX)
        }
        if (filter.fromLocationY != null) {
            query.setParameter("fromLocationY", filter.fromLocationY)
        }
        if (filter.toLocationY != null) {
            query.setParameter("toLocationY", filter.toLocationY)
        }
        if (filter.fromLocationZ != null) {
            query.setParameter("fromLocationZ", filter.fromLocationZ)
        }
        if (filter.toLocationZ != null) {
            query.setParameter("toLocationZ", filter.toLocationZ)
        }
        if (filter.distance != null) {
            query.setParameter("distance", filter.distance)
        }
        if (!filter.nameStartsWithSubstr.isNullOrBlank()) {
            query.setParameter("nameStartsWithSubstr", "${filter.nameStartsWithSubstr}%")
        }
    }
}