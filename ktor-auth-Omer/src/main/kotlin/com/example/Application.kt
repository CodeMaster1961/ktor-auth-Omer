package com.example

import com.example.dao.*
import com.example.plugins.*
import io.ktor.server.application.*

/**
 * @author Ömer Aynaci
 * engine of the application
 */
fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

/**
 * @author Ömer Aynaci
 * all the module functions are executed
 */
fun Application.module() {
    DatabaseFactory.init()
    configureSecurity()
    configureHTTP()
    configureSerialization()
    configureTemplating()
    configureRouting()
}
