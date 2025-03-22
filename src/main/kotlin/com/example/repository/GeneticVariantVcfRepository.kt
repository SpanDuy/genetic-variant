package com.example.repository

import com.example.models.GeneticVariant

interface GeneticVariantVcfRepository {
    fun findVariant(rac: String, lap: Int, rap: Int, refkey: String): GeneticVariant?
}
