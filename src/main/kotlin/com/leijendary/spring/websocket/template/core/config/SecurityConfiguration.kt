package com.leijendary.spring.websocket.template.core.config

import com.leijendary.spring.websocket.template.core.config.properties.AuthProperties
import com.leijendary.spring.websocket.template.core.security.JwtAudienceValidator
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.oauth2.core.DelegatingOAuth2TokenValidator
import org.springframework.security.oauth2.jwt.JwtDecoder
import org.springframework.security.oauth2.jwt.JwtValidators.createDefaultWithIssuer
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder.withJwkSetUri

@Configuration
class SecurityConfiguration(private val authProperties: AuthProperties) {
    @Bean
    fun jwtDecoder(): JwtDecoder {
        val issuer = authProperties.issuer
        val defaultValidator = createDefaultWithIssuer(issuer)
        val audience = authProperties.audience
        val audienceValidator = JwtAudienceValidator(audience)
        val validator = DelegatingOAuth2TokenValidator(defaultValidator, audienceValidator)
        val jwkSetUri = authProperties.jwkSetUri

        return withJwkSetUri(jwkSetUri)
            .build()
            .apply {
                setJwtValidator(validator)
            }
    }
}
