package com.main.services

import com.main.model.User
import com.main.repository.UserRepository
import jakarta.inject.Inject
import jakarta.inject.Singleton
import java.time.Instant


@Singleton
class UserService(
    @Inject private var userRepository: UserRepository,
//                  @Inject private val passwordEncoder: PasswordEncoder
) {
    // Sign up a new user
    fun signUp(username: String, password: String): User {
        // Check if user already exists
        userRepository.findByUsername(username).ifPresent {
            throw IllegalArgumentException("User with username $username already exists")
        }

        // Hash the password
        val hashedPassword = password   // will encode and decode later  // better to hash at the call.
//        val hashedPassword = passwordEncoder.encode(password)

        // Create new user
        val user = User(
            username = username,
            password = hashedPassword,
            createdAt = Instant.now()
        )

        // Save the user to the database
        return userRepository.save(user)
    }
    fun authenticate(username: String, password: String): Boolean {
        val user = userRepository.findByUsername(username).orElseThrow {
            IllegalArgumentException("User not found")
        }

        // Check if the provided password matches the stored password  // change to hash
        return password==user.password
    }
}
