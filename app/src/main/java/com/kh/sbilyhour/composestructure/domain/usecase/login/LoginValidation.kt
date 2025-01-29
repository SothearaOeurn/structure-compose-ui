package com.kh.sbilyhour.composestructure.domain.usecase.login

import android.content.Context
import com.kh.sbilyhour.composestructure.R
import com.kh.sbilyhour.composestructure.domain.Field
import com.kh.sbilyhour.composestructure.domain.ValidationResult

class LoginValidation(private val context: Context) {
    fun validateUsername(username: String): ValidationResult {
        return if (username.isEmpty()) {
            ValidationResult.Error(Field.Username, context.getString(R.string.error_username_empty))
        } else {
            ValidationResult.Success
        }
    }

    fun validatePassword(password: String): ValidationResult {
        return when {
            password.isEmpty() -> ValidationResult.Error(Field.Password, context.getString(R.string.error_password_empty))
            password.length < 6 -> ValidationResult.Error(Field.Password, context.getString(R.string.error_password_length))
            else -> ValidationResult.Success
        }
    }
}

