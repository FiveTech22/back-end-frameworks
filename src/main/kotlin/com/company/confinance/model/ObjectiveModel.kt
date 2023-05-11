package com.company.confinance.model

import net.bytebuddy.dynamic.loading.InjectionClassLoader.Strategy
import java.util.Date
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
    var value: Double,
    var description: String,
    var date: Date,
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    var user: UserModel
)