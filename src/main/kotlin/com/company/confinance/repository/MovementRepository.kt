package com.company.confinance.repository

import com.company.confinance.model.MovementModel
import com.company.confinance.model.UserModel
import org.springframework.data.jpa.repository.JpaRepository

interface MovementRepository : JpaRepository<MovementModel, Long> {

}