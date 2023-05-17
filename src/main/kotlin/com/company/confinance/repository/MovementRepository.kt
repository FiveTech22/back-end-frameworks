package com.company.confinance.repository

import com.company.confinance.model.entity.MovementModel
import org.springframework.data.jpa.repository.JpaRepository

interface MovementRepository : JpaRepository<MovementModel, Long> {
    fun findByUserId(userId: Long): List<MovementModel>

}