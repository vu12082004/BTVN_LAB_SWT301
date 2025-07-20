package com.example.demo.automation.tourify;

import com.example.demo.automation.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage {
    private final By usernameField = By.id("username");
    private final By passwordField = By.id("password");
    private final By loginButton = By.cssSelector("button.btn-login"); // nút đăng nhập
    private final By messageContainer = By.id("messageContainer"); // div hiển thị thông báo lỗi/thành công
    private final By logoutLink = By.id("logout-link"); // nút logout, xác nhận đăng nhập thành công

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void navigate() {
        driver.get("http://localhost:8080/tourify/login");
        waitForVisibility(usernameField, 10); // đợi input username hiện, timeout 10s
    }

    public void enterUsername(String username) {
        waitForVisibility(usernameField, 10);
        type(usernameField, username);
    }

    public void enterPassword(String password) {
        waitForVisibility(passwordField, 10);
        type(passwordField, password);
    }

    public void clickLogin() {
        click(loginButton);
    }

    // Tiện ích đăng nhập đầy đủ để gọi nhanh trong test
    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }

    // Lấy text message hiện tại (nếu có)
    public String getMessage() {
        if (isElementVisible(messageContainer, 5)) {
            return getText(messageContainer).trim();
        }
        return "";
    }

    // Kiểm tra có thông báo lỗi/thành công hiển thị hay không
    public boolean isError() {
        return isElementVisible(messageContainer, 5);
    }

    // Kiểm tra có nút logout hiện (chứng tỏ login thành công)
    public boolean isSuccess() {
        return isElementVisible(logoutLink, 5);
    }

    // Expose locator usernameField để test dùng chờ (wait)
    public By getUsernameField() {
        return usernameField;
    }

    public String getUsernameValidationMessage() {
        WebElement usernameInput = driver.findElement(usernameField);
        return usernameInput.getAttribute("validationMessage");
    }

    public String getPasswordValidationMessage() {
        WebElement passwordInput = driver.findElement(passwordField);
        return passwordInput.getAttribute("validationMessage");
    }

}
