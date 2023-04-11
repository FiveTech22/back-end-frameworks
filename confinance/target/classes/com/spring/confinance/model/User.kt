package com.spring.confinance.model

import javax.persistence.*
import javax.validation.constraints.NotBlank


@Entity
data class User (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Long = 0,

    @get: NotBlank
    val title: String = "",

    @get: NotBlank
    val content: String = ""
)