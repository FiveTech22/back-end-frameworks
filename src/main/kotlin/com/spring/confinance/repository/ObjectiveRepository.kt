package com.spring.confinance.repository

import com.spring.confinance.model.Objective
import org.springframework.data.jpa.repository.JpaRepository

interface ObjectiveRepository : JpaRepository<Objective,Long>
