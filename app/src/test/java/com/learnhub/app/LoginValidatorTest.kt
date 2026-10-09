package com.learnhub.app

import com.learnhub.app.utils.LoginValidator
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import org.junit.Test

class LoginValidatorTest {
    @Test
    fun validEmail_returnsTrue() {
        assertTrue(LoginValidator.validateEmail("user@example.com"))
    }

    @Test
    fun invalidEmail_returnsFalse() {
        assertFalse(LoginValidator.validateEmail("invalid-email"))
    }

    @Test
    fun validPassword_returnsTrue() {
        assertTrue(LoginValidator.validatePassword("abc123"))
    }

    @Test
    fun shortPassword_returnsFalse() {
        assertFalse(LoginValidator.validatePassword("abc"))
    }

    @Test
    fun blankPassword_returnsFalse() {
        assertFalse(LoginValidator.validatePassword(""))
    }
}