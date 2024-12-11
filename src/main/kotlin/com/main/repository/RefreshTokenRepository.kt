package com.main.repository

import com.main.model.RefreshTokenEntity
import io.micronaut.data.jdbc.annotation.JdbcRepository
import io.micronaut.data.repository.CrudRepository
//import jakarta.transaction.Transactional
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import io.micronaut.core.annotation.NonNull
import java.util.Optional
import io.micronaut.data.model.query.builder.sql.Dialect.POSTGRES
import io.micronaut.transaction.annotation.Transactional
import java.time.Instant

@JdbcRepository(dialect = POSTGRES)
interface RefreshTokenRepository : CrudRepository<RefreshTokenEntity, Int> {

    @Transactional
    fun save(
        @NonNull @NotBlank id: Int,
        @NonNull @NotBlank username: String,
        @NonNull @NotBlank refreshToken: String,
        @NonNull @NotNull revoked: Boolean,
        @NonNull @NotNull dateCreated: Instant
    ): RefreshTokenEntity

    fun findByRefreshToken(@NonNull @NotBlank refreshToken: String): Optional<RefreshTokenEntity>

    fun updateByUsername(
        @NonNull @NotBlank username: String,
        revoked: Boolean
    ): Long
}
