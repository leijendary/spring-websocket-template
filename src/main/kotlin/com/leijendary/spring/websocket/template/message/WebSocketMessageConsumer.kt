package com.leijendary.spring.websocket.template.message

import com.leijendary.spring.websocket.template.core.config.service.MessageService
import com.leijendary.spring.websocket.template.core.extension.toClass
import com.leijendary.spring.websocket.template.message.Topic.WEBSOCKET_ANONYMOUS
import com.leijendary.spring.websocket.template.message.Topic.WEBSOCKET_USER
import com.leijendary.spring.websocket.template.model.WebSocketAnonymousMessage
import com.leijendary.spring.websocket.template.model.WebSocketUserMessage
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class WebSocketMessageConsumer(private val messageService: MessageService) {
    @KafkaListener(topics = ["\${spring.kafka.topic.$WEBSOCKET_ANONYMOUS.name}"])
    fun anonymous(json: String) {
        val message: WebSocketAnonymousMessage = json.toClass()

        messageService.send(message.topic, message.payload)
    }

    @KafkaListener(topics = ["\${spring.kafka.topic.$WEBSOCKET_USER.name}"])
    fun user(json: String) {
        val message: WebSocketUserMessage = json.toClass()

        messageService.sendToUser(message.userId, message.topic, message.payload)
    }
}
