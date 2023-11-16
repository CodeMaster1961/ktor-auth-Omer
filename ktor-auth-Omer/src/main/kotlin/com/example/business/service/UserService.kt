package com.example.business.service

import com.example.business.models.*
import com.example.data.models.*
import com.example.data.repositories.*
import kotlinx.coroutines.*
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.experimental.*

class UserService : UserRepository {
    private fun resultToRowUser(row: ResultRow) = User(
        firstName = row[Users.firstName],
        lastName = row[Users.lastName],
        email = row[Users.email],
        password = row[Users.password]
    )

    suspend fun <T> databaseQuery(block: suspend () -> T): T =
        newSuspendedTransaction(Dispatchers.IO) { block() }

    /**
     * @author Ömer Aynaci
     * @param user
     * @return Boolean
     * validating required inputs
     */
    private fun validateInputFields(user: User): Boolean {
        val userModel = UserModel(user.firstName, user.lastName, user.email, user.password)
        if (!userModel.isLengthValid(user.firstName) || !userModel.isLengthValid(user.lastName) || !userModel.isEmailValid(
                user.email
            ) || !userModel.isPasswordValid(user.password)
        ) {
            throw IllegalArgumentException("Invalid credentials")
        } else {
            return true
        }
    }

    /**
     * @author Ömer Aynaci
     * creating a user
     */
    override suspend fun createUser(user: User): Unit = databaseQuery {
        val userModel = UserModel(user.firstName, user.lastName, user.email, user.password)
        if (validateInputFields(user)) {
            Users.insert {
                it[firstName] = user.firstName
                it[lastName] = user.lastName
                it[email] = user.email
                it[password] = userModel.hashPassword()
            }
        }
    }

    /**
     * @author Ömer Aynaci
     * @param user
     * @return a list of users
     * if a user doesn't exist it throws an error otherwise gets all users
     */
    override suspend fun getUsers(user: User): List<User> {
        if (user.toString().isEmpty()) {
            throw IllegalArgumentException("No users found")
        } else {
            return Users.selectAll().map(::resultToRowUser)
        }
    }

    /**
     * @author Ömer Aynaci
     * @param id
     * @return User?
     * if an id is null then it throws an error otherwise it gets the user by the given id
     */
    override suspend fun getUserById(id: Int): User? {
        if (id.equals(null)) {
            throw IllegalArgumentException("No user found with the given id: $id")
        } else {
            return Users.select { Users.userId eq id }
                .map(::resultToRowUser)
                .singleOrNull()
        }
    }

    /**
     * @author Ömer Aynaci
     * @param id
     * @return Boolean
     * if an id is null then it throws an error otherwise it deletes the user by their id.
     */
    override suspend fun deleteUser(id: Int): Boolean {
        if (id == 0) {
            throw IllegalArgumentException("No user found with the given id: $id")
        } else {
            return Users.deleteWhere { Users.userId eq id } > 0
        }
    }
}