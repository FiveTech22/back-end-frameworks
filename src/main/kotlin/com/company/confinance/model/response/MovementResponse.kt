package com.company.confinance.model.response

import java.time.LocalDate

data class MovementResponse(
    var id: Long? = null,
    var type_movement: String,
    var value: Double,
    var description: String,
    var date: LocalDate,
    val userId: Long
)