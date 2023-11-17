package com.example.controllers

import com.example.business.service.*
import com.example.data.models.*
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.response.*

class UserController(private val userService: UserService) {

    /**
     * @author Ömer Aynaci
     * @param user user entity
     * @param request sending the request
     * @param response giving status code and message
     * controller for creating user with responses
     */
    suspend fun createUser(user: User, request: ApplicationRequest, response: ApplicationResponse) {
        try {
            val users = userService.createUser(user)
            request.call.respondText("User has been successfully created", status = HttpStatusCode.Created)
            request.call.respond(users)
        } catch (error: IllegalArgumentException) {
            response.status(HttpStatusCode(400, "Invalid user data: ${error.message}"))
            request.call.respondText("Invalid user data: ${error.message}", status = HttpStatusCode.BadRequest)
        }
    }

    /**
     * @author Ömer Aynaci
     * @param request sending the request
     * @param response giving status code and message
     * controller for getting all users
     */
    suspend fun getAllUsers(request: ApplicationRequest, response: ApplicationResponse) {
        try {
            val users = userService.getUsers()
            response.status(HttpStatusCode(200, "Users found"))
            request.call.respond(users)
        } catch (error: IllegalArgumentException) {
            response.status(HttpStatusCode(400, "User doesn't exists"))
        }
    }

    /**
     * @author Ömer Aynaci
     * @param id the id of the user
     * @param request sending the request
     * @param response giving status code and message
     */
    suspend fun getUserById(id: Int, request: ApplicationRequest, response: ApplicationResponse) {
        try {
            val users = userService.getUserById(id)
            response.status(HttpStatusCode(200, "User received id: $id"))
            request.call.respond(users!!)
        } catch (error: IllegalArgumentException) {
            response.status(HttpStatusCode(400, "User $id doesn't exists"))
        }
    }


    /**
     * @author Ömer Aynaci
     * @param id the id that belongs to the user
     * @param request sending the request
     * @param response giving status code and message
     */
    suspend fun deleteUser(id: Int, request: ApplicationRequest, response: ApplicationResponse) {
        try {
            val users = userService.deleteUser(id)
            request.call.respondText("User successfully deleted", status = HttpStatusCode.NoContent)
            request.call.respond(users)
        } catch (error: IllegalArgumentException) {
            response.status(HttpStatusCode(400, "User not found"))
            request.call.respondText("User not found", status = HttpStatusCode.BadRequest)
        }
    }
}