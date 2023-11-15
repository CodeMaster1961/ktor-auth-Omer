package com.example.util

import com.typesafe.config.Config
import com.typesafe.config.ConfigFactory
import org.jetbrains.exposed.sql.*

val config: Config = ConfigFactory.load()

val database = Database.connect(
    url = config.getString("ktor.database.url"),
    driver = config.getString("ktor.database.driver"),
    user = config.getString("ktor.database.user"),
    password = config.getString("ktor.database.password")
)