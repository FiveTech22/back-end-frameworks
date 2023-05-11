package com.company.confinance.model

import com.fasterxml.jackson.annotation.JsonIdentityReference
import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.boot.autoconfigure.security.SecurityProperties
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "Movement")

data class MovementModel(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(nullable = false)
    var type_movement: String,

    @Column(nullable = false)
    var value: Double,

    var description: String,
    var date: LocalDate,

    @ManyToOne
    val user: UserModel

)

