package com.leijendary.spring.websocket.template.core.util

import com.fasterxml.jackson.databind.ObjectMapper
import com.leijendary.spring.websocket.template.core.util.SpringContext.Companion.getBean
import io.micrometer.tracing.Tracer

object BeanContainer {
    val objectMapper by lazy { getBean(ObjectMapper::class) }
    val tracer by lazy { getBean(Tracer::class) }
}
