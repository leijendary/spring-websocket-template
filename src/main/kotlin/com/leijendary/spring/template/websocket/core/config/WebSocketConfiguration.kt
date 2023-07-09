package com.leijendary.spring.template.websocket.core.config

import com.leijendary.spring.template.websocket.core.config.properties.WebSocketProperties
import com.leijendary.spring.template.websocket.core.interceptor.AuthChannelInterceptor
import org.springframework.context.annotation.Configuration
import org.springframework.messaging.simp.config.ChannelRegistration
import org.springframework.messaging.simp.config.MessageBrokerRegistry
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker
import org.springframework.web.socket.config.annotation.StompEndpointRegistry
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer

const val PREFIX_TOPIC = "/topic"
const val PREFIX_QUEUE = "/queue"

@Configuration
@EnableWebSocketMessageBroker
class WebSocketConfiguration(
    private val authChannelInterceptor: AuthChannelInterceptor,
    private val webSocketProperties: WebSocketProperties
) : WebSocketMessageBrokerConfigurer {
    override fun configureClientInboundChannel(registration: ChannelRegistration) {
        registration.interceptors(authChannelInterceptor)
    }

    override fun configureMessageBroker(registry: MessageBrokerRegistry) {
        registry.setApplicationDestinationPrefixes("/app")
            .enableStompBrokerRelay(PREFIX_TOPIC, PREFIX_QUEUE)
            .setRelayHost(webSocketProperties.relay.host)
            .setRelayPort(webSocketProperties.relay.port)
            .setClientLogin(webSocketProperties.relay.login)
            .setClientPasscode(webSocketProperties.relay.passcode)
            .setSystemLogin(webSocketProperties.relay.login)
            .setSystemPasscode(webSocketProperties.relay.passcode)
    }

    override fun registerStompEndpoints(registry: StompEndpointRegistry) {
        // Standard WebSocket connection
        registry.addEndpoint("/websocket")
            .setAllowedOrigins("*")

        // SockJS support
        registry.addEndpoint("/websocket/sockjs")
            .setAllowedOrigins("*")
            .withSockJS()
    }
}
