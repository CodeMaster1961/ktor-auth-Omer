package com.example.data.repositories

import com.example.business.models.*
import com.example.data.models.*

interface UserRepository {
    suspend fun createUser(user: UserModel): Unit
    suspend fun getUsers(): List<UserModel>
    suspend fun getUserById(id: Int): UserModel?
    suspend fun deleteUser(id: Int): Boolean

}