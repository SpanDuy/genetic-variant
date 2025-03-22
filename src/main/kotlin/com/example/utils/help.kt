package com.example.utils

fun getEnv(key: String, defaultValue: String): String {
    return System.getenv(key) ?: defaultValue
}