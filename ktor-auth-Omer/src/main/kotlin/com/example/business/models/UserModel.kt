package com.example.business.models

import org.mindrot.jbcrypt.*

class UserModel(var firstName: String, var lastName: String, var email: String, private val password: String) {


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
     * @return Boolean
     * validating input fields
     */
    fun isLengthValid(inputField: String, minLength: Int, maxLength: Int): Boolean {
        return inputField.length in minLength..maxLength
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