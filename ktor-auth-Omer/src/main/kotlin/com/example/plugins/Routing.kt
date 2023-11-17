package com.example.plugins

import com.example.business.service.*
import com.example.controllers.*
import com.example.routes.*
import io.ktor.server.application.*
import io.ktor.server.routing.*

/**
 * @author Ömer Aynaci
 * here are the routes configured
 */
fun Application.configureRouting() {
    val userService = UserService()
    val userController = UserController(userService)
    routing {
        UserRoutes(userController).createUser(this)
        UserRoutes(userController).getAllUsers(this)
        UserRoutes(userController).getUserById(this)
    }
}
