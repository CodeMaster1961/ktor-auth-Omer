package com.example.controllers

import com.example.business.service.*
import com.example.data.models.*
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.response.*

class UserController(private val userService: UserService) {

    suspend fun createUser(user: User, request: ApplicationRequest, response: ApplicationResponse) {
        try {
            userService.createUser(user)
            response.status(HttpStatusCode(201, "User has been successfully created"))
        } catch (error: IllegalArgumentException) {
            response.status(HttpStatusCode(400, "Invalid user data: ${error.message}"))
        }
    }
}