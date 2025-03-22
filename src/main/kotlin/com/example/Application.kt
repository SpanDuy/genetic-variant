package com.example

import com.example.controller.configureRouting
import com.example.services.GeneticVariantService
import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    val geneticVariantService = GeneticVariantService()
    module(geneticVariantService)
}

fun Application.module(geneticVariantService: GeneticVariantService) {
    configureRouting(geneticVariantService)
} 