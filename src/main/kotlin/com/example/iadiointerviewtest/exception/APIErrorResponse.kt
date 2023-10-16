package com.example.iadiointerviewtest.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.MissingServletRequestParameterException

@ControllerAdvice
class APIErrorResponse {
    @ExceptionHandler
    fun handlePersonAlreadyExists(exception: PersonAlreadyExists): ResponseEntity<APIErrorMessage> {
        val errorMessage = APIErrorMessage(HttpStatus.BAD_REQUEST.value(), exception.message)
        return ResponseEntity(errorMessage, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler
    fun handlePersonNotFound(exception: PersonNotFound): ResponseEntity<APIErrorMessage> {
        val errorMessage = APIErrorMessage(HttpStatus.BAD_REQUEST.value(), exception.message)
        return ResponseEntity(errorMessage, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler
    fun handleParameterException(exception: MissingServletRequestParameterException): ResponseEntity<APIErrorMessage> {
        val errorMessage = APIErrorMessage(HttpStatus.BAD_REQUEST.value(), "ERROR: Missing parameter - ${exception.message}")
        return ResponseEntity(errorMessage, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler
    fun handleIllegalStateException(exception: IllegalStateException): ResponseEntity<APIErrorMessage> {
        val errorMessage = APIErrorMessage(HttpStatus.BAD_REQUEST.value(), exception.message)
        return ResponseEntity(errorMessage, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler
    fun handleGeneralException(exception: Exception): ResponseEntity<APIErrorMessage> {
        val errorMessage = APIErrorMessage(HttpStatus.BAD_REQUEST.value(), exception.message)
        return ResponseEntity(errorMessage, HttpStatus.BAD_REQUEST)
    }
}

class PersonAlreadyExists(message: String): RuntimeException(message) {}
class PersonNotFound(message: String): RuntimeException(message) {}