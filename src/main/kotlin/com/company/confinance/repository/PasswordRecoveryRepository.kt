package com.company.confinance.repository

import com.company.confinance.model.entity.PasswordRecoveryModel
import org.springframework.data.jpa.repository.JpaRepository

interface PasswordRecoveryRepository: JpaRepository<PasswordRecoveryModel, Long> {
        fun findByEmail(email: String): PasswordRecoveryModel?
}
