package com.kh.sbilyhour.composestructure.domain

// Sealed class to represent validation results
sealed class ValidationResult {
    object Success : ValidationResult()
    data class Error(val field: Field, val message: String) : ValidationResult()
}