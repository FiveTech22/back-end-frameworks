package com.company.confinance.model.response

data class ResetPassword(
    val id: Long,
    val currentPassword: String,
    val newPassword: String
)