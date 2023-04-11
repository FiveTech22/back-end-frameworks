package com.spring.confinance.model

import jakarta.validation.constraints.NotBlank
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class Movement (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Long = 0,

            @get: NotBlank
            val title: String = "",

    @get: NotBlank
    val content: String = ""
)