package com.example.controller

import com.example.dto.ResponseDto
import com.example.dto.geneticVariant.GeneticVariantResponse
import com.example.exception.ExceptionUtils
import com.example.exception.configureExceptionHandling
import com.example.services.GeneticVariantService
import com.example.mapper.GeneticVariantMapper
import htsjdk.variant.variantcontext.VariantContext
import io.ktor.http.*
import io.ktor.serialization.jackson.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.util.*
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.annotation.JsonInclude
import com.example.plugins.applyCommonSettings

fun Application.configureRouting(geneticVariantService: GeneticVariantService) {
    configureExceptionHandling()

    install(ContentNegotiation) {
        jackson {
            applyCommonSettings()
        }
    }

    routing {
        route("/info") {
            get {
                val queryParams = call.request.queryParameters
                val geneticVariantSearchRequest = GeneticVariantMapper.toGeneticVariantSearchRequest(queryParams)

                val variant = geneticVariantService.findVariant(geneticVariantSearchRequest)
                    ?: ExceptionUtils.throwNotFound("Genetic variant not found")

                val response = ResponseDto(
                    body = GeneticVariantMapper.toDto(variant), 
                    error = null
                )
                call.respond(response)
            }
        }
    }
}