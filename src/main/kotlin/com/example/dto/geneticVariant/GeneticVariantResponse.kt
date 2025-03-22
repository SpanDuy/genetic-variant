package com.example.dto.geneticVariant

import kotlinx.serialization.Serializable

@Serializable
data class GeneticVariantResponse(
    val rac: String,          // Reference Assembly Chromosome
    val lap: Int,             // Left Anchor Position
    val rap: Int,             // Right Anchor Position
    val refkey: String,       // Reference Key
    val vcfId: String,        // VCF ID
    val clnsig: String,       // Clinical Significance
    val clnrevstat: String,   // Clinical Review Status
    val clnvc: String         // Clinical Variant Classification
)
