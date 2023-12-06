package com.joux.template.infrastructure.user

import com.template.domain.UserRole.ADMIN
import com.template.domain.UserRole.STANDARD
import com.template.domain.port.UserPort
import com.template.domain.user.model.User
import org.springframework.stereotype.Component
import java.util.*

//@Component
class UserInMermoryAdapter : UserPort {
    val users: List<User> = listOf(
        User(UUID.fromString("6ca10032-e6f4-4c6a-8741-fb20b18b8dcf"), "password", listOf(STANDARD)),
        User(UUID.fromString("261a6ed4-50cf-4c88-bd7d-08b051059c39"), "password", listOf(STANDARD, ADMIN)),
    )

    override fun findById(id: UUID): User? {
        return users.find { user -> user.id == id }
    }

}