package com.leijendary.spring.websocket.template.core.config.service

import com.leijendary.spring.websocket.template.core.config.PREFIX_TOPIC
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.stereotype.Service

@Service
class MessageService(private val messageTemplate: SimpMessagingTemplate) {
    fun send(topic: String, payload: Any) {
        messageTemplate.convertAndSend("$PREFIX_TOPIC/$topic", payload)
    }

    fun sendToUser(userId: String, topic: String, payload: Any) {
        messageTemplate.convertAndSendToUser(userId, "$PREFIX_TOPIC/$topic", payload)
    }
}
