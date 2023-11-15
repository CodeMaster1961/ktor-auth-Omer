package com.example.data.repositories

import com.example.data.models.*

interface UserRepository {
    suspend fun createUser(user: User): Unit
    suspend fun getUsers(): List<User>
    suspend fun getUserById(id: Int): User?
    suspend fun deleteUser(id: Int): Boolean

}