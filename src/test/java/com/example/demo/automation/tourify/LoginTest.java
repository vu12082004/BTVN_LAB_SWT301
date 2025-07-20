package com.example.demo.automation.tourify;

import com.example.demo.automation.base.BaseTest;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LoginTest extends BaseTest {

    private LoginPage loginPage;

    @BeforeEach
    void beforeEach() {
        // 1) Xóa cookies để chắc chắn đang ở state "chưa login"
        driver.manage().deleteAllCookies();

        // 2) Mở thẳng trang login
        driver.get("http://localhost:8080/tourify/login");
        loginPage = new LoginPage(driver);

        // 3) Đợi input username visible
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(loginPage.getUsernameField()));
    }

    @AfterEach
    void afterEach() {
        // Nếu sau khi test xong mà đã login thành công
        if (loginPage.isSuccess()) {
            // Logout tự động
            new LandingPage(driver).logout();
            // Đợi quay về login page
            new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.visibilityOfElementLocated(loginPage.getUsernameField()));
        }
    }

    @Test
    @Order(1)
    void testLoginSuccess() {
        // Act
        loginPage.login("khoanguyen2", "123456");

        // Assert
        Assertions.assertTrue(
                loginPage.isSuccess(),
                "Phải báo đăng nhập thành công với tài khoản hợp lệ"
        );
        // Logout sẽ được xử lý trong @AfterEach
    }

    @Test
    @Order(2)
    void testLoginWrongPassword() {
        loginPage.login("khoanguyen2", "wrongpassword");

        Assertions.assertTrue(
                loginPage.isError(),
                "Phải báo lỗi khi mật khẩu không đúng"
        );
    }

    @Test
    @Order(3)
    void testLoginEmpty() {
        // Chỉ click login mà không nhập gì
        loginPage.clickLogin();

        String userMsg = loginPage.getUsernameValidationMessage();
        String passMsg = loginPage.getPasswordValidationMessage();

        Assertions.assertTrue(
                (userMsg != null && !userMsg.isEmpty()) ||
                        (passMsg != null && !passMsg.isEmpty()),
                "Phải hiển thị validation message khi để trống username hoặc password"
        );
    }

    @ParameterizedTest(name = "[{index}] user={0}, expect={2}")
    @CsvFileSource(resources = "/login-tourify-data.csv", numLinesToSkip = 1)
    @Order(4)
    void testLoginCsv(String username, String password, String expectedResult) {
        loginPage.login(username, password);

        if ("success".equalsIgnoreCase(expectedResult)) {
            Assertions.assertTrue(
                    loginPage.isSuccess(),
                    "Phải login thành công với user=" + username
            );
        } else {
            Assertions.assertTrue(
                    loginPage.isError(),
                    "Phải báo lỗi khi login fail với user=" + username
            );
        }
        // logout (nếu success) sẽ tự động trong @AfterEach
    }
}
