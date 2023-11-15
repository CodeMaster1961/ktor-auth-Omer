package com.example.business.models

import org.mindrot.jbcrypt.*

class UserModel(var firstName: String, var lastName: String, var email: String, private val password: String) {


    /**
     * @author Ömer Aynaci
     * hashing password
     */
    fun hashPassword(): String {
        return BCrypt.hashpw(password, BCrypt.gensalt())
    }


}