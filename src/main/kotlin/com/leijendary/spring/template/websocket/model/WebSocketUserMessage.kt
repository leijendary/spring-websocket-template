package com.leijendary.spring.template.websocket.model

data class WebSocketUserMessage(val userId: String, val topic: String, val payload: Any)
