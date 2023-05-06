package com.company.confinance.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.GenerationType.AUTO
import jakarta.persistence.GenerationType.IDENTITY
import jakarta.persistence.Id
import jakarta.validation.constraints.NotBlank

@Entity
data class UserModel()