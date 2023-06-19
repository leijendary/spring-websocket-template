package com.leijendary.spring.websocket.template.core.extension

import org.slf4j.Logger
import org.slf4j.LoggerFactory.getLogger

inline fun <reified T> T.logger(): Logger = getLogger(T::class.java)
