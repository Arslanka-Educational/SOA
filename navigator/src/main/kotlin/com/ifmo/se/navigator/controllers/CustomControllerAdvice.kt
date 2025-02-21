package com.ifmo.se.navigator.com.ifmo.se.navigator.controllers

import com.ifmo.se.navigator.com.ifmo.se.navigator.exceptions.EntityNotFoundException
import generated.com.ifmo.se.navigator.dto.ErrorDto
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
open class CustomControllerAdvice {
    @ExceptionHandler(EntityNotFoundException::class)
    fun handleEntityNotFoundException(ex: EntityNotFoundException): ResponseEntity<ErrorDto> =
        ResponseEntity(
            ErrorDto(
                code = HttpStatus.NOT_FOUND.name,
                message = ex.message ?: HttpStatus.NOT_FOUND.name
            ), HttpStatus.NOT_FOUND
        )
}