package com.company.confinance.model.response

data class ResetPassword(
    val email: String,
    val currentPassword: String,
    val newPassword: String
)