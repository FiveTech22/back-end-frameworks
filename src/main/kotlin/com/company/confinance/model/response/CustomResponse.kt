package com.company.confinance.model.response

data class CustomResponse(
    val message: String,
    val status: Int,
    var userId: Long? = null
    )