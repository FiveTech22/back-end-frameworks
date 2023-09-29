package com.company.confinance.repository

import com.company.confinance.model.entity.PasswordRecoveryModel
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDateTime

interface PasswordRecoveryRepository: JpaRepository<PasswordRecoveryModel, Long> {
        fun findByEmail(email: String): PasswordRecoveryModel?

        fun deleteByExpirationTimeBefore(expirationTime: LocalDateTime)


        fun findByEmailAndExpirationTimeAfter(email: String, expirationTime: LocalDateTime): PasswordRecoveryModel?

}
