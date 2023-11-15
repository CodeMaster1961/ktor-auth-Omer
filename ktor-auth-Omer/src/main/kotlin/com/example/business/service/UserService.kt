package com.example.business.service

import com.example.business.models.*
import com.example.data.models.*
import com.example.data.repositories.*
import kotlinx.coroutines.*
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.experimental.*

class UserService : UserRepository {

    suspend fun <T> databaseQuery(block: suspend () -> T): T =
        newSuspendedTransaction(Dispatchers.IO) { block() }

    override suspend fun createUser(user: User): Unit = databaseQuery {
        val userModel = UserModel(user.firstName, user.lastName, user.email, user.password)
        if (!userModel.isLengthValid(user.firstName) || !userModel.isLengthValid(user.lastName) || !userModel.isEmailValid(
                user.email
            ) || !userModel.isPasswordValid(user.password)
        ) {
            throw IllegalArgumentException("Invalid credentials")
        } else {
            Users.insert {
                it[firstName] = user.firstName
                it[lastName] = user.lastName
                it[email] = user.email
                it[password] = userModel.hashPassword()
            }
        }
    }

    override suspend fun getUsers(): List<User> {
        TODO("Not yet implemented")
    }

    override suspend fun getUserById(id: Int): User? {
        TODO("Not yet implemented")
    }

    override suspend fun deleteUser(id: Int): Boolean {
        TODO("Not yet implemented")
    }
}