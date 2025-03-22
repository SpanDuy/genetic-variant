package com.example.dto

import kotlinx.serialization.Serializable
import java.lang.Error

@Serializable
data class ResponseDto<T>(
    var body: T?,
    var error: String?,
)
