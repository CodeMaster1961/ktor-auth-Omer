package com.example.plugins

import com.example.business.service.*
import com.example.controllers.*
import com.example.routes.*
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    val userService = UserService()
    val userController = UserController(userService)
    routing {
        UserRoutes(userController).createUser(this)
    }
}
