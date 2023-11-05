package com.company.confinance.model.response

data class PasswordResetRequest(
    val currentPassword: String,
    val newPassword: String
)
