package com.company.confinance.repository

import com.company.confinance.model.entity.ObjectiveModel
import org.springframework.data.jpa.repository.JpaRepository

interface ObjectiveRepository : JpaRepository<ObjectiveModel,Long>{
     fun findByUserId(userId: Long): List<ObjectiveModel>

}