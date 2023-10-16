package com.example.iadiointerviewtest

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class APIErrorResponse {
    @ExceptionHandler
    fun handlePersonAlreadyExists(exception: PersonAlreadyExists): ResponseEntity<APIErrorMessage> {
        val errorMessage = APIErrorMessage(HttpStatus.BAD_REQUEST.value(), exception.message)
        return ResponseEntity(errorMessage, HttpStatus.BAD_REQUEST)
    }
}

class PersonAlreadyExists(message: String): RuntimeException(message) {}