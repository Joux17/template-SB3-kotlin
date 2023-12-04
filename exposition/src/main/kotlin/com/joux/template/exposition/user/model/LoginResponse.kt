package com.joux.template.exposition.user.model

data class LoginResponse(
    val tokenType: String,
    val accessToken: String,
    val refreshToken: String,
) {}