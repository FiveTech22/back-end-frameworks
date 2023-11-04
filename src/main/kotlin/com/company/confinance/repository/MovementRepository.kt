package com.company.confinance.repository

import com.company.confinance.model.entity.MovementModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.util.*

interface MovementRepository : JpaRepository<MovementModel, Long> {
    fun findByUserId(userId: Long): List<MovementModel>

    fun findByParentMovementIdAndFixedIncome(parentMovementId: Long?, fixedIncome: Boolean): List<MovementModel>

    @Query("SELECT m FROM MovementModel m WHERE m.user.id = :userId AND MONTH(STR_TO_DATE(m.date, '%d/%m/%Y')) = :month AND YEAR(STR_TO_DATE(m.date, '%d/%m/%Y')) = :year")
    fun findByUserIdAndMonthandYear(userId: Long, month: Int, year: Int): List<MovementModel>

    fun findByParentMovementId(parentMovementId: Long?): List<MovementModel>

    @Query("SELECT m FROM MovementModel m WHERE m.parentMovementId = :parentId AND m.recurrenceFrequency IS NOT NULL")
    fun findByParentMovementIdAndRecurrenceFrequencyNotNull(@Param("parentId") parentId: Long?): List<MovementModel>

    @Query("SELECT m FROM MovementModel m WHERE m.type_movement= 'receita'")
    fun findRevenues(): List<MovementModel>

    @Query("SELECT m FROM MovementModel m WHERE m.type_movement = 'despesa'")
    fun findExpenses(): List<MovementModel>



}