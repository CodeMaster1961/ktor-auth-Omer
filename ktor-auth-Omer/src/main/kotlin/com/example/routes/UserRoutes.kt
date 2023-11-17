package com.example.routes

import com.example.controllers.*
import com.example.data.models.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.routing.*

class UserRoutes(private val userController: UserController) {

    /**
     * @author Ömer Aynaci
     * creating user at the endpoint /users
     */
    fun createUser(routing: Routing) {
        routing {
            post("/users") {
                val user = call.receive<User>()
                userController.createUser(user, call.request, call.response)
            }
        }
    }

    fun getAllUsers(routing: Routing) {
        routing {
            get("/users") {
                userController.getAllUsers(call.request, call.response)
            }
        }
    }

    fun getUserById(routing: Routing) {
        routing {
            get("/users/{id}") {
                val id = call.parameters["id"]?.toIntOrNull() ?: throw IllegalArgumentException("Invalid id")
                userController.getUserById(id, call.request, call.response)
            }
        }
    }
}