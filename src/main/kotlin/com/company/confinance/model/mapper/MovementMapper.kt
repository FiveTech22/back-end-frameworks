package com.company.confinance.model.mapper

import com.company.confinance.model.entity.MovementModel
import com.company.confinance.model.response.MovementResponse

fun MovementModel.toMovementResponse(): MovementResponse =
    MovementResponse(
        id = this.id,
        type_movement = this.type_movement,
        value = this.value,
        description = this.description,
        date = this.date,
        photo = this.Photo,
        fixedIncome = this.fixedIncome,
        fixedIncomeDurationMonths = this.fixedIncomeDurationMonths,
        userId = this.user.id
    )