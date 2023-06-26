package com.leijendary.spring.websocket.template.core.extension

import com.leijendary.spring.websocket.template.core.util.BeanContainer.objectMapper

inline fun <reified T : Any> String.toClass(): T {
    return objectMapper.readValue(this, T::class.java)
}
