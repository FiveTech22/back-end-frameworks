package com.company.confinance.model.response

import javax.validation.constraints.Null

data class RecoverPassword (
    val email : String,
    val code : String
    )



