package com.joux.template.domain.user.model

import java.util.*

data class UserAuthenticationData(
    val tokenType: String,
    val accessToken: String,
    val refreshToken: UUID,
)