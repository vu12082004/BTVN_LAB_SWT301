package com.example.demo.automation.register;

import com.example.demo.automation.base.BaseTest;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RegisterTest extends BaseTest {
    static RegisterPage registerPage;
    static WebDriverWait wait;

    @BeforeAll
    static void initPage() {
        registerPage = new RegisterPage(driver);
        wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(10));
    }

    @Test
    @Order(1)
    @DisplayName("Đăng ký thành công trên DemoQA")
    void testRegisterSuccess() {
        registerPage.navigate();
        String email = "john" + System.currentTimeMillis() + "@mail.com";
        registerPage.register("John", "Doe", email, "0987654321");
        WebElement success = wait.until(
                ExpectedConditions.visibilityOfElementLocated(registerPage.getSuccessLocator())
        );
        assertTrue(success.getText().contains("Thanks for submitting the form"));
    }

    @ParameterizedTest(name = "Test CSV: {0} {1}, email={2}, expected={4}")
    @CsvFileSource(resources = "/register-data.csv", numLinesToSkip = 1)
    @DisplayName("Đăng ký bằng nhiều dữ liệu khác nhau (CSV)")
    void testRegisterWithCsv(String firstName, String lastName, String email, String mobile, String expected) {
        registerPage.navigate();
        String emailToUse = email.replace("{ts}", String.valueOf(System.currentTimeMillis()));
        registerPage.register(firstName, lastName, emailToUse, mobile);

        if (expected.equalsIgnoreCase("success")) {
            // Thành công: modal phải hiện ra
            boolean visible = registerPage.isElementVisible(registerPage.getSuccessLocator(), 15);
            assertTrue(visible, "Success modal was not displayed!");
        } else {
            // Negative: modal KHÔNG được hiện ra
            boolean visible = registerPage.isElementVisible(registerPage.getSuccessLocator(), 15);
            assertTrue(!visible, "Form failed but success modal was shown!");
        }
    }
}
