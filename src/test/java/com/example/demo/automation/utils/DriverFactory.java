package com.example.demo.automation.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverFactory {
    public static WebDriver createDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito"); // Chạy Chrome ở chế độ ẩn danh (nên có)
        // KHÔNG tắt JavaScript, KHÔNG tắt cookies!
        return new ChromeDriver(options);
    }
}
