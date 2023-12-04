package com.template.domain.port

import com.template.domain.user.model.User
import java.util.UUID

interface UserPort {
    fun findById(id: UUID): User?
}