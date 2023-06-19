package com.leijendary.spring.websocket.template.model

data class WebSocketUserMessage(val userId: String, val topic: String, val payload: Any)
