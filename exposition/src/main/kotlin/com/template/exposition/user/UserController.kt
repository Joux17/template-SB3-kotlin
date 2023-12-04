package com.template.exposition.user

import com.template.exposition.user.model.LoginRequest
import com.template.exposition.user.model.LoginResponse
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/users")
class UserController {
    @PostMapping("/login")
    fun login(request: LoginRequest): LoginResponse {
        return LoginResponse(
            "", "", ""
        )
    }
}