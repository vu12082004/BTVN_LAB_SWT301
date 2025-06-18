package com.example.demo.test_slot2;

import com.example.demo.slot2.InsecureLogin;
import org.junit.jupiter.api.Test;

public class InsecureLoginTest {

    @Test
    void testLoginSuccess() {
        InsecureLogin.login("admin", "123456");
    }

    @Test
    void testLoginFail() {
        InsecureLogin.login("user", "wrongpassword");
    }

    @Test
    void testPrintUserInfo() {
        InsecureLogin insecureLogin = new InsecureLogin();
        insecureLogin.printUserInfo("John Doe");
    }
}
