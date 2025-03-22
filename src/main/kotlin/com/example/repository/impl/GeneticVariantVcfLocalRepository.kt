package com.example.repository.impl

import com.example.exception.ExceptionUtils
import com.example.models.GeneticVariant
import com.example.repository.GeneticVariantVcfRepository
import com.example.mapper.GeneticVariantMapper
import htsjdk.variant.variantcontext.VariantContext
import htsjdk.variant.vcf.VCFCodec
import htsjdk.tribble.AbstractFeatureReader
import htsjdk.tribble.readers.LineIterator
import org.slf4j.LoggerFactory

class GeneticVariantVcfLocalRepository(
    private val dataFilePath: String
) : GeneticVariantVcfRepository {
    
    private val logger = LoggerFactory.getLogger(GeneticVariantVcfLocalRepository::class.java)

    private fun getReader(dataFilePath: String): AbstractFeatureReader<VariantContext, LineIterator> {
        return AbstractFeatureReader.getFeatureReader(
            dataFilePath, 
            VCFCodec(), 
            dataFilePath.endsWith(".gz") || dataFilePath.endsWith(".bgz")
        )
    }

    private fun findWithoutIndex(reader: AbstractFeatureReader<VariantContext, LineIterator>,
                                 rac: String,
                                 lap: Int,
                                 rap: Int
    ): VariantContext? {
        val iterator = reader.iterator()
        while (iterator.hasNext()) {
            val variant = iterator.next()
            if (variant.contig == rac && variant.start >= lap && variant.end <= rap) {
                return variant
            }
        }
        return null
    }

    override fun findVariant(rac: String, lap: Int, rap: Int, refkey: String): GeneticVariant? {
        try {
            val reader = getReader(dataFilePath)
            val variant: VariantContext?

            reader.use { reader ->
                variant = if (reader.hasIndex()) {
                    reader.query(rac, lap, rap).firstOrNull()
                } else {
                    findWithoutIndex(reader, rac, lap, rap)
                }
            }
            return if (variant == null) {
                null
            } else {
                GeneticVariantMapper.toGeneticVariant(variant)
            }
        } catch (e: Exception) {
            logger.error("Ошибка при поиске в VCF-файле: ${e.message}", e)
            ExceptionUtils.throwServerError("Ошибка при поиске в VCF-файле: ${e.message}")
        }
    }
}