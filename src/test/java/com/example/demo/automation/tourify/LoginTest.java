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
    public void setup() {
        loginPage = new LoginPage(driver);
    }

    @Test
    @Order(1)
    public void testLoginSuccess() {
        loginPage.navigate();
        loginPage.login("khoanguyen2", "123456");

        Assertions.assertTrue(loginPage.isSuccess(), "Phải báo đăng nhập thành công");

        // Logout sau khi đăng nhập thành công
        LandingPage landingPage = new LandingPage(driver);
        landingPage.logout();

        // Chờ redirect về trang login với input username hiển thị
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginPage.getUsernameField()));

        Assertions.assertTrue(driver.getCurrentUrl().contains("/login"), "Phải trở về trang login sau logout");
    }

    @Test
    @Order(2)
    public void testLoginWrongPassword() {
        loginPage.navigate();
        loginPage.login("khoanguyen", "wrongpassword");

        Assertions.assertTrue(loginPage.isError(), "Phải báo lỗi đăng nhập khi sai mật khẩu");
    }

    @Test
    @Order(3)
    public void testLoginEmpty() {
        loginPage.navigate();

        // Không nhập gì, chỉ click Login
        loginPage.clickLogin();

        // Kiểm tra trình duyệt có validation message (báo lỗi trường trống)
        String usernameValidationMsg = loginPage.getUsernameValidationMessage();
        String passwordValidationMsg = loginPage.getPasswordValidationMessage();

        Assertions.assertTrue(
                (usernameValidationMsg != null && !usernameValidationMsg.isEmpty()) ||
                        (passwordValidationMsg != null && !passwordValidationMsg.isEmpty()),
                "Phải báo lỗi username hoặc password không được để trống"
        );
    }


    // Test đăng nhập với dữ liệu trong file CSV
    @ParameterizedTest
    @CsvFileSource(resources = "/login-tourify-data.csv", numLinesToSkip = 1)
    public void testLoginCsv(String username, String password, String expectedResult) {
        loginPage.navigate();
        loginPage.login(username, password);

        if ("success".equalsIgnoreCase(expectedResult)) {
            Assertions.assertTrue(loginPage.isSuccess(), "Phải đăng nhập thành công với user: " + username);

            // Logout sau khi đăng nhập thành công
            LandingPage landingPage = new LandingPage(driver);
            landingPage.logout();

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(loginPage.getUsernameField()));

            Assertions.assertTrue(driver.getCurrentUrl().contains("/login"), "Phải trở về trang login sau logout");
        } else {
            Assertions.assertTrue(loginPage.isError(), "Phải báo lỗi đăng nhập với user: " + username);
        }
    }
}
