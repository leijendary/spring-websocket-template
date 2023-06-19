package com.leijendary.spring.websocket.template.core.config

import com.leijendary.spring.websocket.template.core.config.properties.InfoProperties
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn.HEADER
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType.OAUTH2
import io.swagger.v3.oas.annotations.security.SecurityScheme
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpHeaders.AUTHORIZATION

@Configuration
@SecurityScheme(name = AUTHORIZATION, type = OAUTH2, `in` = HEADER)
class OpenAPIConfiguration(private val infoProperties: InfoProperties) {
    @Bean
    fun openAPI(): OpenAPI {
        val app = infoProperties.app
        val api = infoProperties.api
        val info = Info().apply {
            title = app.name
            description = app.description
            termsOfService = api.termsOfService
            contact = api.contact
            license = api.license
            version = app.version
            extensions = api.extensions
        }

        return OpenAPI().info(info)
    }
}
