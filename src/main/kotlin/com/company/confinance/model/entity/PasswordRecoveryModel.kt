package com.company.confinance.model.entity

import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "password_recovery_code")
data class PasswordRecoveryModel (
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,

        @Column(nullable = false)
        val email: String,

        @Column(nullable = false)
        val code: String,

        @Column(nullable = false)
        val expirationTime: LocalDateTime
    )
