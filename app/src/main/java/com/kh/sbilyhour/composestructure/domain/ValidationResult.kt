package com.kh.sbilyhour.composestructure.domain

sealed class ValidationResult {
    data object Success : ValidationResult()
    data class Error(val field: Field, val message: String) : ValidationResult()
}
