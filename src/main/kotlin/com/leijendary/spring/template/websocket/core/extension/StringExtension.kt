package com.leijendary.spring.template.websocket.core.extension

import com.leijendary.spring.template.websocket.core.util.BeanContainer.objectMapper

inline fun <reified T : Any> String.toClass(): T {
    return objectMapper.readValue(this, T::class.java)
}

fun <T> String.toClass(reference: TypeReference<T>): T {
    return objectMapper.readValue(this, reference)
}
