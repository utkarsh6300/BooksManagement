package com.main.model

import jakarta.validation.constraints.NotBlank
import io.micronaut.serde.annotation.Serdeable

@Serdeable
data class SignUpRequest(
    @NotBlank val username: String,
    @NotBlank val password: String
)