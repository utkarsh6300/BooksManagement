package com.main.config

import com.main.model.RefreshTokenEntity
import com.main.repository.RefreshTokenRepository
import io.micronaut.security.authentication.Authentication
import io.micronaut.security.errors.OauthErrorResponseException
import io.micronaut.security.token.event.RefreshTokenGeneratedEvent
import io.micronaut.security.token.refresh.RefreshTokenPersistence
import jakarta.inject.Singleton
import org.reactivestreams.Publisher
import reactor.core.publisher.Flux
import reactor.core.publisher.FluxSink
import io.micronaut.security.errors.IssuingAnAccessTokenErrorCode.INVALID_GRANT
import java.time.Instant
import java.util.Optional

// reference https://guides.micronaut.io/latest/micronaut-security-jwt-gradle-groovy.html

@Singleton
class CustomRefreshTokenPersistence(private val refreshTokenRepository: RefreshTokenRepository) : RefreshTokenPersistence {

    override fun persistToken(event: RefreshTokenGeneratedEvent) {
        if (event.refreshToken !=null && event.authentication?.name !=null) {
                     val payload : String = event.refreshToken
                    refreshTokenRepository.save(1,event.authentication.name, payload, false,Instant.now())
        }
    }

    override fun getAuthentication(refreshToken: String): Publisher<Authentication> {
        return Flux.create { emitter: FluxSink<Authentication> ->
            val tokenOpt: Optional<RefreshTokenEntity> = refreshTokenRepository.findByRefreshToken(refreshToken)
            if (tokenOpt.isPresent) {
                val token = tokenOpt.get()
                if (token.revoked) {
                    emitter.error(OauthErrorResponseException(INVALID_GRANT, "refresh token revoked", null))
                } else {
                    emitter.next(Authentication.build(token.username))
                    emitter.complete()
                }
            } else {
                emitter.error(OauthErrorResponseException(INVALID_GRANT, "refresh token not found", null))
            }
        }
    }
}
