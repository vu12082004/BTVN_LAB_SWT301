package com.example.demo.slot2;

import org.jetbrains.annotations.NotNull;

import java.util.logging.Logger;

public class InsecureLogin {

    private static final Logger logger = Logger.getLogger(InsecureLogin.class.getName());

    public static void login(@NotNull String username, String password) {
        String envPassword = System.getenv("ADMIN_PASSWORD");
        if (username.equals("admin") && password.equals(envPassword)) {
            logger.info("Login successful");
        } else {
            logger.warning("Login failed");
        }
    }


    public void printUserInfo(String user) {
        if (user != null && !user.isEmpty()) {
            logger.info("User: " + user);
        }
    }
}
