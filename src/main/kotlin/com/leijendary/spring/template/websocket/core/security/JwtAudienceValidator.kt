package com.leijendary.spring.template.websocket.core.security

import org.springframework.security.oauth2.core.OAuth2Error
import org.springframework.security.oauth2.core.OAuth2ErrorCodes.INVALID_TOKEN
import org.springframework.security.oauth2.core.OAuth2TokenValidator
import org.springframework.security.oauth2.core.OAuth2TokenValidatorResult
import org.springframework.security.oauth2.core.OAuth2TokenValidatorResult.failure
import org.springframework.security.oauth2.core.OAuth2TokenValidatorResult.success
import org.springframework.security.oauth2.jwt.Jwt

class JwtAudienceValidator(private val audience: String) : OAuth2TokenValidator<Jwt> {
    override fun validate(jwt: Jwt): OAuth2TokenValidatorResult {
        val hasAudience = jwt.audience.contains(audience)

        if (hasAudience) {
            return success()
        }

        val error = OAuth2Error(INVALID_TOKEN)

        return failure(error)
    }
}
