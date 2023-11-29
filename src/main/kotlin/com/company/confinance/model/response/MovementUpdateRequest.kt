package com.company.confinance.model.response

data class MovementUpdateRequest(
    val description: String?,
    val photo: Int?,
    val value: Double? = null,
    val date: String?,
    val fixedIncome: Boolean?,
    val recurrenceFrequency: String?,
    val recurrenceIntervals: Int?,
)
