package com.ifmo.se.navigator.com.ifmo.se.navigator.clients

import feign.Response
import feign.codec.ErrorDecoder
import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException

class CustomErrorDecoder : ErrorDecoder {
    override fun decode(methodKey: String?, response: Response?): java.lang.Exception? {
        return when (response?.status()) {
            HttpStatus.NOT_FOUND.value() -> ResponseStatusException(HttpStatus.NOT_FOUND, "Resource not found")
            else -> feign.FeignException.errorStatus(methodKey, response)
        }
    }
}