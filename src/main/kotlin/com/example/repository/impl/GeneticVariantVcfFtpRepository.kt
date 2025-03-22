package com.example.repository.impl

import com.example.exception.ExceptionUtils
import com.example.models.GeneticVariant
import com.example.repository.GeneticVariantVcfRepository
import org.slf4j.LoggerFactory


class GeneticVariantVcfFtpRepository(
    private val ftpHost: String,
    private val ftpPath: String
) : GeneticVariantVcfRepository {
    
    private val logger = LoggerFactory.getLogger(GeneticVariantVcfFtpRepository::class.java)

    override fun findVariant(rac: String, lap: Int, rap: Int, refkey: String): GeneticVariant? {
        // TODO: Implement the logic to find the variant on the ftp server
        logger.error("Not implemented")
        ExceptionUtils.throwServerError("Not implemented")
    }
} 