package com.example.mapper

import com.example.dto.geneticVariant.GeneticVariantResponse
import com.example.dto.geneticVariant.GeneticVariantSearchRequest
import com.example.exception.ExceptionUtils
import com.example.models.GeneticVariant
import io.ktor.http.Parameters
import htsjdk.variant.variantcontext.VariantContext

object GeneticVariantMapper {
    
    fun toDto(geneticVariant: GeneticVariant): GeneticVariantResponse {
        return GeneticVariantResponse(
            rac = geneticVariant.rac,
            lap = geneticVariant.lap,
            rap = geneticVariant.rap,
            refkey = geneticVariant.refkey,
            vcfId = geneticVariant.vcfId,
            clnsig = geneticVariant.clnsig,
            clnrevstat = geneticVariant.clnrevstat,
            clnvc = geneticVariant.clnvc
        )
    }

    fun toGeneticVariantSearchRequest(queryParams: Parameters): GeneticVariantSearchRequest {
        return GeneticVariantSearchRequest(
            rac = queryParams["rac"] ?: ExceptionUtils.throwBadRequest("Parameter 'rac' is required"),
            lap = queryParams["lap"]?.toIntOrNull() ?: ExceptionUtils.throwBadRequest("Parameter 'lap' must be a number"),
            rap = queryParams["rap"]?.toIntOrNull() ?: ExceptionUtils.throwBadRequest("Parameter 'rap' must be a number"),
            refkey = queryParams["refkey"] ?: ExceptionUtils.throwBadRequest("Parameter 'refkey' is required")
        )
    }

    fun toGeneticVariant(variantContext: VariantContext): GeneticVariant {
        return GeneticVariant(
            rac = variantContext.contig,
            lap = variantContext.start,
            rap = variantContext.end,
            refkey = variantContext.reference.baseString,
            vcfId = variantContext.id,
            clnsig = variantContext.attributes.getOrDefault("CLNSIG", "Unknown").toString(),
            clnrevstat = variantContext.attributes.getOrDefault("CLNREVSTAT", "Unknown").toString(),
            clnvc = variantContext.attributes.getOrDefault("CLNVC", "Unknown").toString()
        )
    }
}