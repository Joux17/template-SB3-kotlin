package com.joux.template.exposition.user.model

import com.joux.template.domain.user.model.UserAuthenticationData

data class LoginResponse(
    val tokenType: String,
    val accessToken: String,
    val refreshToken: String,
) {
    constructor(userAuthenticationData: UserAuthenticationData) : this(
        userAuthenticationData.tokenType,
        userAuthenticationData.accessToken,
        userAuthenticationData.refreshToken.toString()
    )
}