package com.example.routes

import com.example.controllers.*
import com.example.data.models.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.routing.*

class UserRoutes(private val userController: UserController) {

    fun createUser(routing: Routing) {
        routing {
            post("/users") {
                val user = call.receive<User>()
                userController.createUser(user,call.request,call.response)
            }
        }
    }
}