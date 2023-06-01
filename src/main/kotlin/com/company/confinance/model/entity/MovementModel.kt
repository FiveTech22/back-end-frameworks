package com.company.confinance.model.entity

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.time.LocalDate
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

    var date: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties(value = ["name", "email", "password"])
    val user: UserModel

)

