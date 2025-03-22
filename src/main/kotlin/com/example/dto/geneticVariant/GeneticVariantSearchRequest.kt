package com.example.dto.geneticVariant

import kotlinx.serialization.Serializable

@Serializable
data class GeneticVariantSearchRequest(
    val rac: String,          // Reference Assembly Chromosome
    val lap: Int,             // Left Anchor Position
    val rap: Int,             // Right Anchor Position
    val refkey: String        // Reference Key
)
