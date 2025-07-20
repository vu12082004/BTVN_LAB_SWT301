package com.example.demo.automation.base;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Chờ cho element hiển thị trên DOM
    protected WebElement waitForVisibility(By locator, int i) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    // Cuộn element vào giữa màn hình (tránh bị quảng cáo/iframe che mất)
    protected void scrollToElement(By locator) {
        WebElement element = waitForVisibility(locator, 5);
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center'});", element);
    }

    // Click element (nên scroll trước khi click)
    protected void click(By locator) {
        scrollToElement(locator);
        waitForVisibility(locator, 5).click();
    }

    // Gõ text vào field, chỉ sendKeys nếu text != null
    protected void type(By locator, String text) {
        WebElement element = waitForVisibility(locator, 5);
        element.clear();
        if (text != null) {
            element.sendKeys(text);
        }
    }

    // Lấy text của element
    protected String getText(By locator) {
        return waitForVisibility(locator, 5).getText();
    }

    // Điều hướng tới URL
    public void navigateTo(String url) {
        driver.get(url);
    }

    // Kiểm tra element có hiển thị không (dùng trong test assertion)
    public boolean isElementVisible(By locator, int timeoutSeconds) {
        try {
            WebDriverWait customWait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
            return customWait.until(ExpectedConditions.visibilityOfElementLocated(locator)).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }


    public void selectDropdownByVisibleText(By locator, String visibleText) {
        WebElement dropdown = driver.findElement(locator);
        Select select = new Select(dropdown);
        select.selectByVisibleText(visibleText);
    }
}
