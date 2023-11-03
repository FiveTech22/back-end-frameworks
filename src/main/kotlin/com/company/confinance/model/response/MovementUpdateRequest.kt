package com.company.confinance.model.response

data class MovementUpdateRequest(
    val description: String?,
    val type_movement: String?,
    val photo: Int?,
    val value: Double?,
    val date: String?,
    val fixedIncome: Boolean?,
    val recurrenceFrequency: String?,
    val recurrenceIntervals: Int?,
    val parentMovementId: Long? = null
)
