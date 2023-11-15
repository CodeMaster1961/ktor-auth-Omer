package com.example.plugins

import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.response.*

/**
 * @author Ömer Aynaci
 * serialization is here configured for converting json objects into kotlin objects
 */
fun Application.configureSerialization() {
    install(ContentNegotiation) {
        json()
    }
}
