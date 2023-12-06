package com.joux.template.infrastructure.user

import com.joux.template.infrastructure.user.model.UserEntity
import com.joux.template.infrastructure.user.repository.UserJpaRepository
import com.template.domain.port.UserPort
import com.template.domain.user.model.User
import org.springframework.stereotype.Component
import java.util.*

@Component
class UserJpaAdapter(
    private val userJpaRepository: UserJpaRepository,
) : UserPort {

    override fun findById(id: UUID): User? {
        return userJpaRepository.findById(id)
            .map(UserEntity::toDomain)
            .orElse(null)
    }
}