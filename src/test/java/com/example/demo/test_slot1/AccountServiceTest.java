package com.example.demo.test_slot1;

import com.example.demo.slot1.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.*;

public class AccountServiceTest {
    private AccountService service;

    @BeforeEach
    void setUp() {
        service = new AccountService();
    }

    // Test chính cho đăng ký tài khoản với dữ liệu từ file
    @ParameterizedTest(name = "Test {index} => username={0}, password={1}, email={2}, expected={3}")
    @CsvFileSource(resources = "/test-data.csv", numLinesToSkip = 1)
    @DisplayName("Test registerAccount with CSV data")
    void testRegisterAccountFromFile(String username, String password, String email, boolean expected) {
        boolean result = service.registerAccount(username, password, email);
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Valid email should pass format check")
    void testValidEmail() {
        assertTrue(service.isValidEmail("user@example.com"));
    }

    @Test
    @DisplayName("Invalid email format should fail")
    void testInvalidEmailFormat() {
        assertFalse(service.isValidEmail("userexample.com"));
        assertFalse(service.isValidEmail("user@.com"));
        assertFalse(service.isValidEmail(null));
    }

    @Test
    @DisplayName("Valid password should pass (length > 6)")
    void testValidPassword() {
        assertTrue(service.isValidPassword("abcdefg"));
    }

    @Test
    @DisplayName("Invalid password (null or too short) should fail")
    void testInvalidPassword() {
        assertFalse(service.isValidPassword("123"));
        assertFalse(service.isValidPassword(null));
    }

    @Test
    @DisplayName("Valid username should pass")
    void testValidUsername() {
        assertTrue(service.isUsernameValid("john_doe"));
    }

    @Test
    @DisplayName("Invalid username (null or empty) should fail")
    void testInvalidUsername() {
        assertFalse(service.isUsernameValid(null));
        assertFalse(service.isUsernameValid(""));
        assertFalse(service.isUsernameValid("   "));
    }

    @Test
    @DisplayName("Duplicate email should not allow registration")
    void testDuplicateEmail() {
        assertTrue(service.registerAccount("john", "password123", "john@domain.com"));
        assertFalse(service.registerAccount("john2", "password456", "john@domain.com")); // trùng email
    }

    @Test
    @DisplayName("Email uniqueness check should work")
    void testEmailUniqueness() {
        assertTrue(service.isEmailUnique("a@b.com"));
        service.registerAccount("user", "password123", "a@b.com");
        assertFalse(service.isEmailUnique("a@b.com"));
    }

    @Test
    @DisplayName("Reset should clear registered emails")
    void testResetFunctionality() {
        service.registerAccount("john", "password123", "john@domain.com");
        assertFalse(service.isEmailUnique("john@domain.com"));
        service.reset();
        assertTrue(service.isEmailUnique("john@domain.com"));
    }
}
