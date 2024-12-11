package com.main.repository

import com.main.model.User
import io.micronaut.data.jdbc.annotation.JdbcRepository
import io.micronaut.data.model.query.builder.sql.Dialect.POSTGRES
import io.micronaut.data.repository.CrudRepository
import java.util.Optional

@JdbcRepository( dialect = POSTGRES)
interface UserRepository : CrudRepository<User, Long> {

    // Find a user by username
    fun findByUsername(username: String): Optional<User>
}
