package org.example.com.ifmo.se.route.management.data.repositories.specifications

import generated.com.ifmo.se.route.dto.GetRoutesFilterParameterDto
import org.springframework.data.jpa.domain.Specification
import jakarta.persistence.criteria.Predicate
import org.example.com.ifmo.se.route.management.data.models.Coordinates
import org.example.com.ifmo.se.route.management.data.models.Location
import org.example.com.ifmo.se.route.management.data.models.Route

class RouteSpecification(private val filter: GetRoutesFilterParameterDto) : Specification<Route> {
    override fun toPredicate(
        root: jakarta.persistence.criteria.Root<Route>,
        query: jakarta.persistence.criteria.CriteriaQuery<*>,
        criteriaBuilder: jakarta.persistence.criteria.CriteriaBuilder
    ): Predicate? {
        val predicates = mutableListOf<Predicate>()

        filter.minId?.let { predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get<Int>("id"), it)) }
        filter.maxId?.let { predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get<Int>("id"), it)) }
        filter.name?.let { predicates.add(criteriaBuilder.equal(root.get<String>("name"), it)) }
        filter.nameStartsWithSubstr?.let { predicates.add(criteriaBuilder.like(root.get("name"), "${it}%")) }
        filter.minX?.let { predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get<Coordinates>("coordinates").get<Int>("x"), it)) }
        filter.maxX?.let { predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get<Coordinates>("coordinates").get<Int>("x"), it)) }
        filter.minY?.let { predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get<Coordinates>("coordinates").get<Double>("y"), it)) }
        filter.maxY?.let { predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get<Coordinates>("coordinates").get<Double>("y"), it)) }
        filter.minCreationDate?.let { predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get<java.time.OffsetDateTime>("creationDate"), it)) }
        filter.maxCreationDate?.let { predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get<java.time.OffsetDateTime>("creationDate"), it)) }
        filter.fromLocationX?.let { predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get<Location>("from").get<Int>("x"), it)) }
        filter.toLocationX?.let { predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get<Location>("to").get<Int>("x"), it)) }
        filter.fromLocationY?.let { predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get<Location>("from").get<Int>("y"), it)) }
        filter.toLocationY?.let { predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get<Location>("to").get<Int>("y"), it)) }
        filter.fromLocationZ?.let { predicates.add(criteriaBuilder.equal(root.get<Location>("from").get<Long>("z"), it)) }
        filter.toLocationZ?.let { predicates.add(criteriaBuilder.equal(root.get<Location>("to").get<Long>("z"), it)) }
        filter.distance?.let { predicates.add(criteriaBuilder.equal(root.get<Double>("distance"), it)) }

        return criteriaBuilder.and(*predicates.toTypedArray())
    }
}
