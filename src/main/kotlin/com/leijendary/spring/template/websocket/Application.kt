package com.leijendary.spring.template.websocket

import org.springframework.boot.SpringBootVersion
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.core.env.get
import org.springframework.retry.annotation.EnableRetry

@EnableRetry
@SpringBootApplication
class Application

fun main(args: Array<String>) {
    runApplication<com.leijendary.spring.template.websocket.Application>(*args) {
        setBanner { environment, _, out ->
            val name = environment["info.app.name"]
            val version = environment["info.app.version"]
            val springVersion = SpringBootVersion.getVersion()

            out.print("Running $name v$version on Spring Boot v$springVersion")
        }
    }
}
