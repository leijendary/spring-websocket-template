package com.leijendary.spring.websocket.template.core.config.properties

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "auth")
class AuthProperties {
    var audience: String = ""
    var issuer: String = ""
    var jwkSetUri: String = ""
}
