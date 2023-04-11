package com.spring.confinance.repository

import com.spring.confinance.model.Movement
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MovementRepository : JpaRepository<Movement, Long>

