package com.example.dao

import com.example.data.models.*
import com.example.util.*
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.*


object DatabaseFactory {

    /**
     * @author Ömer Aynaci
     * creating the tables and sending it to mysql workbench
     */
    fun init() {
        transaction(database) {
            SchemaUtils.create(Users)
        }
    }
}