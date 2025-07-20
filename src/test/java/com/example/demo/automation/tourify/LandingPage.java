package com.example.demo.automation.tourify;

import com.example.demo.automation.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LandingPage extends BasePage {
    // Cập nhật selector cho logout button chính xác theo HTML thực tế của trang landing/dashboard
    private final By logoutButton = By.id("logout-link"); // Thay đổi nếu id khác

    public LandingPage(WebDriver driver) {
        super(driver);
    }

    public void logout() {
        waitForVisibility(logoutButton, 5);
        click(logoutButton);
    }

    public boolean isAtLoginPage() {
        return driver.getCurrentUrl().contains("/login");
    }
}
