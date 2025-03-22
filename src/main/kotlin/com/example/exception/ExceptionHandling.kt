package com.example.exception

import com.example.dto.ResponseDto
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*

fun Application.configureExceptionHandling() {
    install(StatusPages) {
        // Обработка IllegalArgumentException (неверные входные данные)
        exception<IllegalArgumentException> { call, cause ->
            val response = ResponseDto<Any>(body = null, error = cause.message)
            call.respond(HttpStatusCode.BadRequest, response)
        }

        // Обработка NoSuchElementException (ресурс не найден)
        exception<NoSuchElementException> { call, cause ->
            val response = ResponseDto<Any>(body = null, error = cause.message ?: "Ресурс не найден")
            call.respond(HttpStatusCode.NotFound, response)
        }

        // Общая обработка всех остальных исключений
        exception<Throwable> { call, cause ->
            val response = ResponseDto<Any>(body = null, error = "Внутренняя ошибка сервера: ${cause.message}")
            call.respond(HttpStatusCode.InternalServerError, response)
        }
    }
}