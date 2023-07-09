package com.leijendary.spring.template.websocket.core.interceptor

import com.leijendary.spring.template.websocket.core.extension.logger
import org.springframework.context.MessageSource
import org.springframework.context.i18n.LocaleContextHolder.getLocale
import org.springframework.http.HttpHeaders.AUTHORIZATION
import org.springframework.messaging.Message
import org.springframework.messaging.MessageChannel
import org.springframework.messaging.simp.stomp.StompCommand.CONNECT
import org.springframework.messaging.simp.stomp.StompCommand.ERROR
import org.springframework.messaging.simp.stomp.StompHeaderAccessor
import org.springframework.messaging.support.ChannelInterceptor
import org.springframework.messaging.support.MessageBuilder.createMessage
import org.springframework.messaging.support.MessageHeaderAccessor
import org.springframework.security.oauth2.jwt.JwtDecoder
import org.springframework.security.oauth2.jwt.JwtException
import org.springframework.stereotype.Component
import java.security.Principal

private const val PREFIX_BEARER = "Bearer "

@Component
class AuthChannelInterceptor(
    private val jwtDecoder: JwtDecoder,
    private val messageSource: MessageSource
) : ChannelInterceptor {
    private val log = logger()

    override fun preSend(message: Message<*>, channel: MessageChannel): Message<*>? {
        val accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor::class.java) ?: return message

        if (accessor.command != CONNECT) {
            return message
        }

        val authorization = accessor.getFirstNativeHeader(AUTHORIZATION)?.replace(PREFIX_BEARER, "") ?: return message
        val jwt = try {
            jwtDecoder.decode(authorization)
        } catch (exception: JwtException) {
            return handle(exception)
        }

        accessor.user = Principal { jwt.subject }

        return message
    }

    private fun handle(it: Throwable): Message<*> {
        val message = when (it) {
            is JwtException -> {
                val message = it.message!!

                log.warn(message)

                val code = if (message.contains("expired")) "access.expired" else "access.invalid"

                messageSource.getMessage(code, emptyArray(), getLocale())
            }

            else -> it.message
        }
        val accessor = StompHeaderAccessor.create(ERROR)
        accessor.message = message

        return createMessage(byteArrayOf(), accessor.messageHeaders)
    }
}
