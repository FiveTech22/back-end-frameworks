package com.company.confinance.model.response

data class ValidatePassword (
    val email: String,
    val code: String,
    val newPassword: String
        )

