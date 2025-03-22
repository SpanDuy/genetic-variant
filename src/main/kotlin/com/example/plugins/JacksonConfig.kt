package com.example.plugins

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.annotation.JsonInclude

/**
 * Применяет общие настройки Jackson к переданному ObjectMapper
 */
fun ObjectMapper.applyCommonSettings() = apply {
    disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
    enable(SerializationFeature.INDENT_OUTPUT)
    disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
    propertyNamingStrategy = PropertyNamingStrategies.SNAKE_CASE
    setSerializationInclusion(JsonInclude.Include.NON_NULL)
} 