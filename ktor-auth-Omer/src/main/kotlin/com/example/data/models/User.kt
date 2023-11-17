package com.example.data.models


import org.jetbrains.exposed.sql.*


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