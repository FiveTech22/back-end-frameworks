package com.company.confinance.repository

import com.company.confinance.model.entity.UserModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository :JpaRepository<UserModel,Long>{
    fun findByEmail(email: String): UserModel?

    fun findByEmailAndPassword(email: String, password: String): UserModel?


}