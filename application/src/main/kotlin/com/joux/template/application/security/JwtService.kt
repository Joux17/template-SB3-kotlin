package com.joux.template.application.security

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.exceptions.JWTVerificationException
import com.template.domain.user.model.User
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.time.ZonedDateTime

@Service
class JwtService(
    @Value("\${application.security.jwt.secretKey}")
    private val secretKey: String,
    @Value("\${application.security.jwt.expirationDelayInMinutes}")
    private val tokenExpirationDelyInMinutes: Long,
) {
    private val algorithm = Algorithm.HMAC512(secretKey)
    private val jwtVerifier = JWT.require(algorithm).build()
    private fun getAlgorithm(): Algorithm {
        return algorithm;
    }

    fun createJwt(user: User): String {
        val jwtExpirationDate = ZonedDateTime.now().plusMinutes(tokenExpirationDelyInMinutes)

        return JWT.create()
            .withSubject(user.id.toString())
            .withExpiresAt(jwtExpirationDate.toInstant())
            .sign(algorithm)
    }

    fun isValid(token: String): Boolean {
        return try {
            jwtVerifier.verify(token)
            true
        } catch (exception: JWTVerificationException) {
            false
        }
    }

    fun extractUsername(token: String): String {
        return JWT.decode(token).subject
    }
}