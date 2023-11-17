package com.example.routes

import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.server.testing.*
import org.junit.Test
import kotlin.test.*

class UserRoutesTest {
    @Test
    fun test_Create_User_When_Valid_Credentials_Endpoint() = testApplication {
        val response = client.post("/users") {
            header(HttpHeaders.ContentType, ContentType.Application.Json)
            setBody(
                """{
  "firstName": "Keesss",
  "lastName": "Kaasss",
  "email": "keeskaas@hotmail.com",
  "password": "Keeskaas1233#$%"
}"""
            )
        }
        assertEquals(HttpStatusCode.Created, response.status)
    }
}