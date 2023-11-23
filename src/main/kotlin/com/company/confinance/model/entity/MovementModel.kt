package com.company.confinance.model.entity

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
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

    @Column
    var description: String,

    @Column(nullable = false)
    var date: String,

    @Column(nullable = false)
    var photo: Int = 0,

    @Column(nullable = false)
    var fixedIncome: Boolean,

    @Column
    var recurrenceFrequency: String?, /*"daily", "weekly", "monthly", "annually"*/

    @Column
    var recurrenceIntervals: Int?,

    var parentMovementId: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties(value = ["name", "email", "password","photo"])
    val user: UserModel

)

