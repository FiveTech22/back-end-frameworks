package com.company.confinance.model.entity

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.time.LocalDate
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
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

    @Column(nullable = false)
    var savedValue: Double,

    @Column(nullable = false)
    var name: String,

    @Column(nullable = false)
    var photo: Int = 0,

    @Column(nullable = false)
    var date: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties(value = ["name", "email", "password", "photo"])
    val user: UserModel? = null

)