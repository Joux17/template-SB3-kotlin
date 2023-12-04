package com.template.domain.user.model

import com.template.domain.UserRole
import java.util.*

data class User(
    val id: UUID,
    val password: String,
    val roles: List<UserRole>,
)