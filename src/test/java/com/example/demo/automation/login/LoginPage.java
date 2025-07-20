package com.example.demo.automation.login;

import com.example.demo.automation.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {
    private By usernameField = By.id("username");
    private By passwordField = By.id("password");
    private By loginButton = By.cssSelector("button[type='submit']");
    private By successMsg = By.cssSelector(".flash.success");
    private By errorMsg = By.cssSelector(".flash.error");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void navigate() {
        navigateTo("https://the-internet.herokuapp.com/login");
    }

    public void login(String username, String password) {
        type(usernameField, username);
        type(passwordField, password);
        click(loginButton);
    }

    public By getSuccessLocator() {
        return successMsg;
    }

    public By getErrorLocator() {
        return errorMsg;
    }
}
