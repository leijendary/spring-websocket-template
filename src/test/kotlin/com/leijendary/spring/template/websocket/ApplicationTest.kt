package com.leijendary.spring.template.websocket

import com.leijendary.spring.template.websocket.container.JaegerContainerTest
import com.leijendary.spring.template.websocket.container.KafkaContainerTest
import com.leijendary.spring.template.websocket.container.RabbitContainerTest
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration

@SpringBootTest
@ContextConfiguration(
    initializers = [
        JaegerContainerTest.Initializer::class,
        KafkaContainerTest.Initializer::class,
        RabbitContainerTest.Initializer::class,
    ]
)
@AutoConfigureMockMvc
class ApplicationTest {
    @Test
    fun contextLoads() {
    }
}
