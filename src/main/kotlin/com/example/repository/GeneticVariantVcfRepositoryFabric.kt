package com.example.repository

import com.example.repository.impl.GeneticVariantVcfFtpRepository
import com.example.repository.impl.GeneticVariantVcfLocalRepository
import com.example.repository.impl.RepositoryType
import com.example.utils.getEnv

object GeneticVariantVcfRepositoryFabric {

    private val dataFilePath = getEnv("GENETIC_DATA_FILE_PATH", "./data/clinvar.vcf.gz")
    private val ftpHost = getEnv("FTP_HOST", "ftp.ncbi.nlm.nih.gov")
    private val ftpPath = getEnv("FTP_PATH", "/pub/clinvar/vcf_GRCh38/clinvar.vcf.gz")

    fun createRepository(type: RepositoryType): GeneticVariantVcfRepository {
        return when (type) {
            RepositoryType.LOCAL -> {
                GeneticVariantVcfLocalRepository(dataFilePath)
            }
            RepositoryType.CLINVAR_FTP -> {
                GeneticVariantVcfFtpRepository(ftpHost, ftpPath)
            }
        }
    }
}