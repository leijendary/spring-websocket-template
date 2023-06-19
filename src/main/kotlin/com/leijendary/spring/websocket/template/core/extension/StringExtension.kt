package com.leijendary.spring.websocket.template.core.extension

import com.leijendary.spring.websocket.template.core.util.BeanContainer.objectMapper
import java.lang.Character.toLowerCase
import java.lang.Character.toUpperCase

inline fun <reified T : Any> String.toClass(): T {
    return objectMapper.readValue(this, T::class.java)
}

fun String.snakeCaseToCamelCase(capitalizeFirst: Boolean = false): String {
    val builder = StringBuilder()

    this.split("_".toRegex())
        .toTypedArray()
        .forEach { builder.append(it.upperCaseFirst()) }

    val result = builder.toString()

    return if (!capitalizeFirst) result.lowerCaseFirst() else result
}

fun String.upperCaseFirst(): String {
    val chars = this.toCharArray()
    chars[0] = toUpperCase(chars[0])

    return String(chars)
}

fun String.lowerCaseFirst(): String {
    val chars = this.toCharArray()
    chars[0] = toLowerCase(chars[0])

    return String(chars)
}

fun String.isInt(): Boolean {
    return try {
        this.toInt()
        true
    } catch (ignored: NumberFormatException) {
        false
    }
}

fun String.isLong(): Boolean {
    return try {
        this.toLong()
        true
    } catch (ignored: NumberFormatException) {
        false
    }
}
