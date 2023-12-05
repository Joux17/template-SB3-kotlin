package com.joux.template.application.security

import com.joux.template.application.security.model.CustomUserDetails
import com.joux.template.application.user.UserUseCases
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Component
import java.util.*

@Component
class CustomUserDetailsService(
    private val userUseCases: UserUseCases,
) : UserDetailsService {

    // Appelé dans la mécanique Spring lors d'un appel au back
    override fun loadUserByUsername(username: String?): UserDetails {
        val userId = parseUserId(username)
        val user = userUseCases.findById(userId) ?: throw UsernameNotFoundException(username)

        return CustomUserDetails(user)
    }

    private fun parseUserId(username: String?): UUID {
        try {
            return UUID.fromString(username)
        } catch (exception: IllegalArgumentException) {
            throw UsernameNotFoundException(username)
        }
    }
}