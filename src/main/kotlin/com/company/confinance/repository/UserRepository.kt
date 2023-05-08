package com.company.confinance.repository

import com.company.confinance.model.UserModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository :JpaRepository<UserModel,Long>