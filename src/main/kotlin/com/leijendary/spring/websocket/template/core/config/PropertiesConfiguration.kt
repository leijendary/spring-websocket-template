package com.leijendary.spring.websocket.template.core.config

import com.leijendary.spring.websocket.template.core.config.properties.AuthProperties
import com.leijendary.spring.websocket.template.core.config.properties.InfoProperties
import com.leijendary.spring.websocket.template.core.config.properties.KafkaTopicProperties
import com.leijendary.spring.websocket.template.core.config.properties.WebSocketProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@EnableConfigurationProperties(
    AuthProperties::class,
    InfoProperties::class,
    KafkaTopicProperties::class,
    WebSocketProperties::class
)
class PropertiesConfiguration 
