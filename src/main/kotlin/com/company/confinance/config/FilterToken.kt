package com.company.confinance.config

import com.company.confinance.repository.UserRepository
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import java.io.IOException
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class FilterToken(
    /*
    private val tokenService: TokenService,
    private val userRepository: UserRepository
) : OncePerRequestFilter()

    @Throws(ServletException::class, IOException::class)
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val token: String
        val authorizationHeader = request.getHeader("Authorization")

        if (authorizationHeader != null  && authorizationHeader.startsWith("Bearer "))  {
            token = authorizationHeader.substring(7)
            val subject = tokenService.getSubject(token)
            val user= userRepository.findByEmail(subject)
            val authentication = UsernamePasswordAuthenticationToken(user, null,
                (user?.roles ?: emptyList()) as MutableCollection<out GrantedAuthority>?
            )
            SecurityContextHolder.getContext().authentication = authentication
        }

        filterChain.doFilter(request, response)
    }
}
*/
)