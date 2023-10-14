package com.company.confinance.config

import com.company.confinance.model.entity.UserModel
import com.company.confinance.repository.UserRepository
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.Date

@Service
class TokenService {

    private val secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256)


    fun gerarToken(usuario: UserModel): String {
        return Jwts.builder()
            .setIssuer("User")
            .setSubject(usuario.email)
            .claim("id", usuario.id)
            .setExpiration(
                Date.from(
                    LocalDateTime.now()
                        .plusMinutes(30)
                        .toInstant(ZoneOffset.of("-03:00"))
                )
            )
            .signWith(secretKey)
            .compact()
    }

    fun getSubject(token: String): String {
        return Jwts.parserBuilder()
            .setSigningKey(secretKey)
            .build()
            .parseClaimsJws(token)
            .body
            .subject
    }
}