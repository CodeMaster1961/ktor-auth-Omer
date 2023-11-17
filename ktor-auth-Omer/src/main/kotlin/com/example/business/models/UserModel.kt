package com.example.business.models

import kotlinx.serialization.Serializable
import org.mindrot.jbcrypt.*

@Serializable
data class UserModel(var firstName: String, var lastName: String, var email: String, private val password: String) {


    /**
     * @author Ömer Aynaci
     * @return String
     * hashing password
     */
    fun hashPassword(): String {
        return BCrypt.hashpw(password, BCrypt.gensalt())
    }

    /**
     * @author Ömer Aynaci
     * @return String
     * returns the password
     */
    fun getPassword(): String {
        return password
    }
    /**
     * @author Ömer Aynaci
     * @return Boolean
     * validating input fields
     */
    fun isLengthValid(inputField: String): Boolean {
        return inputField.length in 5..15
    }

    /**
     * @author Ömer Aynaci
     * @return Boolean
     * checks if email is valid
     */
    fun isEmailValid(email: String): Boolean {
        val regexEmail = Regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}\$")
        return email.matches(regexEmail)
    }

    /**
     * @author Ömer Aynaci
     * @return Boolean
     * checks if password is valid
     */
    fun isPasswordValid(password: String): Boolean {
        val regexPassword = Regex("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@#\$%^&+=])([A-Za-z\\d@#\$%^&+=]){8,}\$")
        return password.matches(regexPassword)
    }


}