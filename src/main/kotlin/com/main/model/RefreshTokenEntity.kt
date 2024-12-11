package com.main.model

import io.micronaut.core.annotation.NonNull
import io.micronaut.data.annotation.DateCreated
import io.micronaut.data.annotation.GeneratedValue
import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.MappedEntity
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import java.time.Instant

@MappedEntity("refresh_token")
data class RefreshTokenEntity(

    @Id
    @GeneratedValue
    val id: Int,

    @NonNull
    @NotBlank
    val username: String,

    @NonNull
    @NotBlank
    val refreshToken: String,

    @NonNull
    @NotBlank
    val revoked: Boolean,

    @DateCreated
    val dateCreated: Instant
)
