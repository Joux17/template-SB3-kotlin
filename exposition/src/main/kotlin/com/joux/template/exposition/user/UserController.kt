package com.joux.template.exposition.user

import com.joux.template.application.user.UserUseCases
import com.joux.template.exposition.user.model.LoginRequest
import com.joux.template.exposition.user.model.LoginResponse
import org.springframework.security.access.annotation.Secured
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.authorization.AuthorityAuthorizationManager.hasRole
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api/users")
class UserController(
    private val userUseCases: UserUseCases,
) {
    @PostMapping("/login")
    fun login(@RequestBody request: LoginRequest): LoginResponse {
        val userAuthenticationData = userUseCases.login(
            UUID.fromString(request.id),
            request.password
        )

        return LoginResponse(userAuthenticationData)
    }

    @GetMapping("/traitementAdministrateur")
//    @PreAuthorize("hasRole('ADMIN')") // Pas utile car déjà vérifié dans SecurityConfiguration
    fun traitementAdministrateur(): String {
        return "traitementAdmin"
    }

    @GetMapping("/test")
    fun login(): String {
        return "coucou"
    }
}