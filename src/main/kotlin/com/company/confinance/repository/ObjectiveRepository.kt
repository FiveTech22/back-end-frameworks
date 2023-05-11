package com.company.confinance.repository

import com.company.confinance.model.ObjectiveModel


interface ObjectiveRepository {
    abstract fun save(objective: ObjectiveModel): ObjectiveModel
    abstract fun deleteById(id: Long)
    infix fun FindAll(unit: Unit) {

    }

    fun FindAll(): List<ObjectiveModel>
}