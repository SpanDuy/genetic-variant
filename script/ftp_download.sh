#! /bin/bash
if [ ! -f ./data/clinvar.vcf.gz ]; then
    echo "Загрузка файла clinvar.vcf.gz..."
    wget -O ./data/clinvar.vcf.gz "ftp://${FTP_HOST:-ftp.ncbi.nlm.nih.gov}${FTP_PATH:-/pub/clinvar/vcf_GRCh38/clinvar.vcf.gz}"
fi
if [ ! -f ./data/clinvar.vcf.gz.tbi ]; then
    echo "Загрузка файла clinvar.vcf.gz.tbi..."
    wget -O ./data/clinvar.vcf.gz.tbi "ftp://${FTP_HOST:-ftp.ncbi.nlm.nih.gov}${FTP_PATH:-/pub/clinvar/vcf_GRCh38/clinvar.vcf.gz}.tbi"
fi