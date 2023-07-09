package com.leijendary.spring.template.websocket.core.config.properties

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("spring.web-socket")
class WebSocketProperties {
    val relay = Relay()

    class Relay {
        lateinit var host: String
        var port = 61613
        lateinit var login: String
        lateinit var passcode: String
    }
}
