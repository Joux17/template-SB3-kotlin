package com.joux.template.infrastructure.user.model

import com.template.domain.UserRole
import com.template.domain.user.model.User
import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "`user`")
data class UserEntity(
    @Id
    val id: UUID,
    @Column(nullable = false)
    val password: String,
    @ElementCollection(targetClass = UserRole::class)
    @CollectionTable(
        name = "user_role",
        joinColumns = [JoinColumn(name = "user_id")]
    )
    val roles: List<UserRole>, // Ce n'est pas utile de créer un UserRole dans l'infrastructure car c'est juste une enum
) {
    fun toDomain(): User {
        return User(
            id,
            password,
            roles = listOf()
        )
    }
}