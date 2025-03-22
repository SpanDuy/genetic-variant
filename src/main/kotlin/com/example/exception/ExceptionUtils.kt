package com.example.exception

object ExceptionUtils {
    fun throwBadRequest(message: String): Nothing = throw IllegalArgumentException(message)
    fun throwNotFound(message: String): Nothing = throw NoSuchElementException(message)
    fun throwServerError(message: String): Nothing = throw RuntimeException(message)
}