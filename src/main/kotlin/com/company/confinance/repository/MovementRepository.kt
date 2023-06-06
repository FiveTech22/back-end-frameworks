package com.company.confinance.repository

import com.company.confinance.model.entity.MovementModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.*

interface MovementRepository : JpaRepository<MovementModel, Long> {
    fun findByUserId(userId: Long): List<MovementModel>

}