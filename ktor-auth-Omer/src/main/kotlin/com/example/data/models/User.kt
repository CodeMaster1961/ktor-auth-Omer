package com.example.data.models

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.isNotNull


/**
 * @author Ömer Aynaci
 * holding the data about a user
 */
@Serializable
data class User(val firstName: String, val lastName: String, val email: String, val password: String)

/**
 * @author Ömer Aynaci
 * making the user table
 */
object Users : Table() {
    val userId = integer("userId").autoIncrement()
    val firstName = varchar("firstName", length = 130)
    val lastName = varchar("lastName", length = 130)
    val email = varchar("email", length = 130)
    val password = varchar("password", length = 200)

    override val primaryKey = PrimaryKey(userId)
}