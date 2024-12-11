package com.main.model

import io.micronaut.data.annotation.GeneratedValue
import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.MappedEntity
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import java.time.Instant

@MappedEntity("user_password")
data class User(
    @Id @GeneratedValue
    val id: Long? = null,

    @NotBlank
    val username: String,

    @NotBlank
    val password: String,  // The password will be hashed

    @NotNull
    val createdAt: Instant = Instant.now()
)
