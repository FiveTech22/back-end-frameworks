package com.company.confinance.repository

import com.company.confinance.model.entity.MovementModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.*

interface MovementRepository : JpaRepository<MovementModel, Long> {
    fun findByUserId(userId: Long): List<MovementModel>

    @Query("SELECT m FROM MovementModel m WHERE m.user.id = :userId AND MONTH(STR_TO_DATE(m.date, '%d/%m/%Y')) = :month AND YEAR(STR_TO_DATE(m.date, '%d/%m/%Y')) = :year")
    fun findByUserIdAndMonthandYear(userId: Long, month: Int, year: Int): List<MovementModel>




}