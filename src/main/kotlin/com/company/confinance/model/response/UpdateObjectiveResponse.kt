package com.company.confinance.model.response

data class UpdateObjectiveResponse (

    val value: Double?,
    val savedValue : Double?,
    val name: String?,
    val photo: Int = 0 ,
    val date: String?
)
