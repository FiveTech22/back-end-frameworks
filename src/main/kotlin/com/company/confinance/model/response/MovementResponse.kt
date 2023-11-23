package com.company.confinance.model.response

import java.time.LocalDate


data class MovementResponse(
    var id: Long? = null,
    var type_movement: String,
    var value: Double,
    var description: String,
    var date: String,
    var photo : Int = 0,
    var fixedIncome : Boolean,
    var recurrenceFrequency: String?,
    var  recurrenceIntervals : Int?,
    var parentMovementId: Long? = null,
    val userId: Long
)