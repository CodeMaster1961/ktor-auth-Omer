package com.example.data.models

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.isNotNull


/**
 * @author Ömer Aynaci
 * holding the data about a user
 */
data class User(val firstName: String, val lastName: String, val email: String, val password: String)

/**
 * @author Ömer Aynaci
 * making the user table
 */
object Users : Table() {
    val userId = integer("userId").autoIncrement()
    val firstName = varchar("firstName", length = 130).isNotNull()
    val lastName = varchar("lastName", length = 130).isNotNull()
    val email = varchar("email", length = 130).isNotNull()
    val password = varchar("password", length = 200).isNotNull()

    override val primaryKey = PrimaryKey(userId)
}