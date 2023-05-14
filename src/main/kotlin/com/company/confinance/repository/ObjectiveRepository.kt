package com.company.confinance.repository

import com.company.confinance.model.ObjectiveModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

interface ObjectiveRepository : JpaRepository<ObjectiveModel,Long>{
     fun findByUserId(userId: Long): List<ObjectiveModel>

}