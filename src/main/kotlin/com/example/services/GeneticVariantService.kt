package com.example.services

import com.example.dto.geneticVariant.GeneticVariantSearchRequest
import com.example.models.GeneticVariant
import org.slf4j.LoggerFactory
import com.example.repository.GeneticVariantVcfRepository
import com.example.repository.GeneticVariantVcfRepositoryFabric
import com.example.repository.impl.RepositoryType
import java.util.concurrent.ConcurrentHashMap

class GeneticVariantService {
    
    private val logger = LoggerFactory.getLogger(GeneticVariantService::class.java)

    private val cache = ConcurrentHashMap<GeneticVariantSearchRequest, GeneticVariant?>()

    private val repository: GeneticVariantVcfRepository =
        GeneticVariantVcfRepositoryFabric.createRepository(RepositoryType.LOCAL)

    private fun searchInTabixFile(request: GeneticVariantSearchRequest): GeneticVariant? {
        logger.info("Find variant: $request")

        return repository.findVariant(request.rac, request.lap, request.rap, request.refkey)
    }

    fun findVariant(request: GeneticVariantSearchRequest): GeneticVariant? {
        logger.info("Find variant: $request")
        
        return cache.computeIfAbsent(request) { req ->
            logger.info("Cache miss for request: $req")
            searchInTabixFile(req).also {
                logger.info("Adding result to cache: ${it != null}")
            }
        }
    }

} 