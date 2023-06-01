package com.company.confinance.model.entity

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.time.LocalDate
import java.util.Date
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table


@Entity
@Table (name = "Objective")

data class ObjectiveModel (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(nullable = false)
    var value: Double,

    var name: String,

    var date: LocalDate,

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties(value = ["name", "email", "password"])
    val user: UserModel? = null

)