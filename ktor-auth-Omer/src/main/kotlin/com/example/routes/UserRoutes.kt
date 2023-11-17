package com.example.routes

import com.example.business.models.*
import com.example.controllers.*
import com.example.data.models.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

class UserRoutes(private val userController: UserController) {

    /**
     * @author Ömer Aynaci
     * @param routing defined for doing a request
     * creating user at the endpoint /users
     */
    fun createUser(routing: Routing) {
        routing {
            post("/users") {
                val user = call.receive<UserModel>()
                userController.createUser(user, call.request, call.response)
            }
        }
    }

    /**
     * @author Ömer Aynaci
     * @param routing defined for doing a request
     * getting all users
     */
    fun getAllUsers(routing: Routing) {
        routing {
            get("/users") {
                userController.getAllUsers(call.request, call.response)
            }
        }
    }

    /**
     * @author Ömer Aynaci
     * @param routing defined for doing a request
     * getting a user by a sepcific id
     */
    fun getUserById(routing: Routing) {
        routing {
            get("/users/{id}") {
                val id = call.parameters["id"]?.toIntOrNull() ?: throw IllegalArgumentException("Invalid id")
                userController.getUserById(id, call.request, call.response)
            }
        }
    }

    /**
     * @author Ömer Aynaci
     * @param routing defined for doing a request
     * deleting a user by a specific id
     */
    fun deleteUser(routing: Routing) {
        routing {
            delete("/users/{id}") {
                val id = call.parameters["id"]?.toIntOrNull() ?: throw IllegalArgumentException("Invalid id")
                userController.deleteUser(id,call.request,call.response)
            }
        }
    }
}