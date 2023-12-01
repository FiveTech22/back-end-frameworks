package com.company.confinance.model.entity

import javax.persistence.CollectionTable
import javax.persistence.Column
import javax.persistence.ElementCollection
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.Table
import javax.validation.constraints.Email

@Entity
@Table(name = "User")
data class UserModel(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @Column(nullable = false)
    var name: String = "",

    var photo: Int?,

    @Column(nullable = false)
    @Email(regexp = "/^[a-zA-Z0-9.!#\$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*\$/.")
    var email: String = "",

    @Column(nullable = false)
    var password: String = ""
)
