package com.main.controller

import com.main.model.SignUpRequest
import com.main.services.UserService
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.security.annotation.Secured
import io.micronaut.security.rules.SecurityRule
import jakarta.inject.Inject


@Controller("/signup")
class UserController(@Inject private val userService: UserService) {

    @Secured(SecurityRule.IS_ANONYMOUS)
    @Post
    fun signUp(@Body signUpRequest: SignUpRequest): HttpResponse<String> {
        try {
            // Call the service to create the user
            userService.signUp(signUpRequest.username, signUpRequest.password)
            return HttpResponse.created("User signup successfully.")
        } catch (e: IllegalArgumentException) {
            return HttpResponse.notFound("User already exist")
        }
    }
}
