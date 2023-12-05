package com.joux.template.application.security.model

import com.template.domain.UserRole
import com.template.domain.user.model.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

data class CustomUserDetails(
    private val user: User,
) : UserDetails {
    // Sorte de Mapper pour faire matcher les roles des users avec un objet compréhensible par Spring
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return user.roles
            .map(UserRole::name)
            .map { roleName -> "ROLE_$roleName" } // car convention de SpringSecurity qui prefixe les roles avec ROLE_
            .map(::SimpleGrantedAuthority)
            .toMutableList()
    }

    override fun getPassword(): String {
        return user.password
    }

    override fun getUsername(): String {
        return user.id.toString()
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }

}