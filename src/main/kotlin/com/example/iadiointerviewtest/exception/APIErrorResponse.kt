package com.example.iadiointerviewtest.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.MissingServletRequestParameterException

@ControllerAdvice
class APIErrorResponse {

    /**
     * Exception to be thrown when a person already exists in the database.
     * @param exception PersonAlreadyExists exception
     * @return ResponseEntity Sends error message to the API
     */

    @ExceptionHandler
    fun handlePersonAlreadyExists(exception: PersonAlreadyExists): ResponseEntity<APIErrorMessage> {
        val errorMessage = APIErrorMessage(HttpStatus.BAD_REQUEST.value(), exception.message)
        return ResponseEntity(errorMessage, HttpStatus.BAD_REQUEST)
    }

    /**
     * Exception to be thrown when a person cannot be found in the database.
     * @param exception PersonNotFound exception
     * @return ResponseEntity Sends error message to the API
     */

    @ExceptionHandler
    fun handlePersonNotFound(exception: PersonNotFound): ResponseEntity<APIErrorMessage> {
        val errorMessage = APIErrorMessage(HttpStatus.BAD_REQUEST.value(), exception.message)
        return ResponseEntity(errorMessage, HttpStatus.BAD_REQUEST)
    }

    /**
     * Exception to be thrown when a parameter isn't provided in the API
     * @param exception MissingServletRequestParameterException exception
     * @return ResponseEntity Sends error message to the API
     */

    @ExceptionHandler
    fun handleParameterException(exception: MissingServletRequestParameterException): ResponseEntity<APIErrorMessage> {
        val errorMessage = APIErrorMessage(HttpStatus.BAD_REQUEST.value(), "ERROR: Missing parameter - ${exception.message}")
        return ResponseEntity(errorMessage, HttpStatus.BAD_REQUEST)
    }

    /**
     * Illegal state exception handler
     * @param exception IllegalStateException exception
     * @return ResponseEntity Sends error message to the API
     */

    @ExceptionHandler
    fun handleIllegalStateException(exception: IllegalStateException): ResponseEntity<APIErrorMessage> {
        val errorMessage = APIErrorMessage(HttpStatus.BAD_REQUEST.value(), exception.message)
        return ResponseEntity(errorMessage, HttpStatus.BAD_REQUEST)
    }

    /**
     * General exception handler
     * @param exception Exception exception
     * @return ResponseEntity Sends error message to the API
     */

    @ExceptionHandler
    fun handleGeneralException(exception: Exception): ResponseEntity<APIErrorMessage> {
        val errorMessage = APIErrorMessage(HttpStatus.BAD_REQUEST.value(), exception.message)
        return ResponseEntity(errorMessage, HttpStatus.BAD_REQUEST)
    }

    /**
     * Exception to be thrown when illegal age data is provided to the API
     * @param exception IllegalAgeConversion exception
     * @return ResponseEntity Sends error message to the API
     */

    @ExceptionHandler
    fun handleIllegalAgeConversion(exception: IllegalAgeConversion): ResponseEntity<APIErrorMessage> {
        val errorMessage = APIErrorMessage(HttpStatus.BAD_REQUEST.value(), exception.message)
        return ResponseEntity(errorMessage, HttpStatus.BAD_REQUEST)
    }
}

//Classes for my custom exceptions

class PersonAlreadyExists(message: String): RuntimeException(message) {}
class PersonNotFound(message: String): RuntimeException(message) {}
class IllegalAgeConversion(message: String): RuntimeException(message) {}