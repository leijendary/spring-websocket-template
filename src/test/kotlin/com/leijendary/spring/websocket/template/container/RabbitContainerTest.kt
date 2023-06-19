package com.leijendary.spring.websocket.template.container

import org.springframework.boot.test.util.TestPropertyValues
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.ConfigurableApplicationContext
import org.testcontainers.containers.RabbitMQContainer
import org.testcontainers.utility.DockerImageName

class RabbitContainerTest {
    companion object {
        private val image = DockerImageName.parse("rabbitmq:3-management-alpine")
        private val rabbit = RabbitMQContainer(image).withPluginsEnabled("rabbitmq_stomp").apply {
            addExposedPort(61613)
        }
    }

    internal class Initializer : ApplicationContextInitializer<ConfigurableApplicationContext> {
        override fun initialize(applicationContext: ConfigurableApplicationContext) {
            rabbit.start()

            val properties = arrayOf(
                "spring.webSocket.relay.host=${rabbit.host}",
                "spring.webSocket.relay.port=${rabbit.httpPort}",
                "spring.webSocket.relay.login=${rabbit.adminUsername}",
                "spring.webSocket.relay.passcode=${rabbit.adminPassword}"
            )

            TestPropertyValues
                .of(*properties)
                .applyTo(applicationContext.environment)
        }
    }
}
