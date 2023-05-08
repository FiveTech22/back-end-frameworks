package com.company.confinance.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table
import javax.validation.constraints.Email
import javax.validation.constraints.Size

@Entity
@Table(name = "User")
data class UserModel(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id:Long,

    @Column(nullable = false)
    @Size(min = 3, max = 30)
    val nome: String = "",

    @Column(nullable = false)
    @Email
    val email: String = "",

    @Column(nullable = false)
    val password: String = ""
)
