package com.leijendary.spring.websocket.template.core.extension

import com.leijendary.spring.websocket.template.core.util.BeanContainer.objectMapper
import java.lang.reflect.Field

fun Any.toJson(): String {
    return objectMapper.writeValueAsString(this)
}

fun Any.reflectField(property: String): Field {
    val field = try {
        this.javaClass.getDeclaredField(property)
    } catch (_: NoSuchFieldException) {
        this.javaClass.superclass.getDeclaredField(property)
    }
    field.isAccessible = true

    return field
}

fun Any.reflectGet(property: String): Any? = reflectField(property).get(this)

fun Any.reflectSet(property: String, value: Any?): Any? {
    val field = reflectField(property)
    field.set(this, value)

    return field.get(this)
}
