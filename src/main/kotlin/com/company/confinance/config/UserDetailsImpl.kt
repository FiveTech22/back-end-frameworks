package com.company.confinance.config

import com.company.confinance.model.entity.UserModel
import com.company.confinance.repository.UserRepository
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
@Service
class UserDetailsImpl (private val userRepository: UserRepository) : UserDetailsService {

    override fun loadUserByUsername(email: String): UserDetails {
        val user: UserModel = userRepository.findByEmail(email)
            ?: throw UsernameNotFoundException("Email não encontrado: $email")

        return User(user.email, user.password, emptyList())
    }
}
