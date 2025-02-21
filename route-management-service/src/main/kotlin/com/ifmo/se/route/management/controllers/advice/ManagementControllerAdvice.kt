package org.example.com.ifmo.se.route.management.controllers.advice

import generated.com.ifmo.se.route.dto.ErrorDto
import jakarta.persistence.EntityNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
open class ManagementControllerAdvice {
    @ExceptionHandler(EntityNotFoundException::class)
    fun handleEntityNotFoundException(ex: EntityNotFoundException): ResponseEntity<ErrorDto> =
        ResponseEntity(
            ErrorDto(
                code = HttpStatus.NOT_FOUND.name,
                message = ex.message ?: HttpStatus.NOT_FOUND.name
            ), HttpStatus.NOT_FOUND
        )
}