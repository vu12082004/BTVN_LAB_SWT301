package com.example.demo.automation.login;

import com.example.demo.automation.base.BaseTest;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("Login Tests using Selenium & JUnit 5")
public class LoginTest extends BaseTest {
    static LoginPage loginPage;
    static WebDriverWait wait;

    @BeforeAll
    static void initPage() {
        loginPage = new LoginPage(driver);
        wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(10));
    }

    @Test
    @Order(1)
    @DisplayName("Should login successfully with valid credentials")
    void testLoginSuccess() {
        loginPage.navigate();
        loginPage.login("tomsmith", "SuperSecretPassword!");
        WebElement success = wait.until(ExpectedConditions.visibilityOfElementLocated(loginPage.getSuccessLocator()));
        assertTrue(success.getText().contains("You logged into a secure area!"));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/login-data.csv", numLinesToSkip = 1)
    @Order(2)
    @DisplayName("Login with multiple test data")
    void testLoginMultiple(String username, String password, String expected) {
        loginPage.navigate();
        username = username == null ? "" : username.trim();
        password = password == null ? "" : password.trim();
        loginPage.login(username, password);
        By resultLocator = expected.equals("success") ? loginPage.getSuccessLocator() : loginPage.getErrorLocator();
        WebElement result = wait.until(ExpectedConditions.visibilityOfElementLocated(resultLocator));
        if (expected.equals("success")) {
            assertTrue(result.getText().contains("You logged into a secure area!"));
        } else {
            assertTrue(result.getText().toLowerCase().contains("invalid"));
        }
    }
}
