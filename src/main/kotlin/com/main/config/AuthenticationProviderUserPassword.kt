package com.main.config

import com.main.services.UserService
import io.micronaut.core.annotation.NonNull
import io.micronaut.core.annotation.Nullable
import io.micronaut.http.HttpRequest
import io.micronaut.security.authentication.AuthenticationFailureReason
import io.micronaut.security.authentication.AuthenticationRequest
import io.micronaut.security.authentication.AuthenticationResponse
import io.micronaut.security.authentication.provider.HttpRequestAuthenticationProvider
import jakarta.inject.Inject
import jakarta.inject.Singleton

// reference https://guides.micronaut.io/latest/micronaut-security-jwt-gradle-groovy.html

@Singleton
class AuthenticationProviderUserPassword<B> : HttpRequestAuthenticationProvider<B> {
    @Inject
    lateinit var userService : UserService;
    override fun authenticate(
        @Nullable httpRequest: HttpRequest<B>?,
        @NonNull authenticationRequest: AuthenticationRequest<String, String>
    ): AuthenticationResponse {

        return if (userService.authenticate(authenticationRequest.identity,authenticationRequest.secret)) {
            AuthenticationResponse.success(authenticationRequest.identity)
        } else {
            AuthenticationResponse.failure(AuthenticationFailureReason.CREDENTIALS_DO_NOT_MATCH)
        }
    }
}
