package com.joux.template.exposition.user

import com.joux.template.application.UserUseCases
import com.joux.template.exposition.user.model.LoginRequest
import com.joux.template.exposition.user.model.LoginResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/api/users")
class UserController(
    private val userUseCases: UserUseCases,
) {
    @PostMapping("/login")
    fun login(request: LoginRequest): LoginResponse {
        val userAuthenticationData = userUseCases.login(
            UUID.fromString(request.id),
            request.password
        )

        return LoginResponse(userAuthenticationData)
    }

    @GetMapping("/test")
    fun login(): String {
        return "coucou"
    }
}