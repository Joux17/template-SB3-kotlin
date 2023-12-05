package com.joux.template.application.user

import com.joux.template.application.security.JwtService
import com.joux.template.domain.exception.AuthenticationFailedException
import com.joux.template.domain.user.exception.UserNotFoundException
import com.joux.template.domain.user.model.UserAuthenticationData
import com.template.domain.port.UserPort
import com.template.domain.user.model.User
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserUseCases(
    private val jwtService: JwtService,
    private val userPort: UserPort,
) {
    fun login(id: UUID, password: String): UserAuthenticationData {
        val user = userPort.findById(id) ?: throw UserNotFoundException(id)

        if (user.password != password) {
            throw AuthenticationFailedException
        }

        return UserAuthenticationData(
            tokenType = "Bearer",
            accessToken = jwtService.createJwt(user),
            refreshToken = UUID.randomUUID()
        )
    }

    fun findById(username: UUID): User? {
        return userPort.findById(username)
    }
}