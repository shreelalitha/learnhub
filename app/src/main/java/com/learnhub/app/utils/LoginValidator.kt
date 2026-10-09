package com.learnhub.app.utils


object LoginValidator {

    fun validateEmail(email: String): Boolean {
        val pattern = Regex(
            "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"
        )
        return pattern.matches(email.trim())
    }

    fun validatePassword(pwd: String): Boolean {
        return pwd.isNotBlank() && pwd.length >= 6
    }
}
