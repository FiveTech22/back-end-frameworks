package com.company.confinance.model

import java.util.*
import javax.persistence.*

@Entity
@Table( name = "Movement")

data class MovementModel(
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
var id: Long? = null,
var type_movement: String,
var value: Double,
var description: String,
var date: Date,
@ManyToOne
var user: UserModel
)



