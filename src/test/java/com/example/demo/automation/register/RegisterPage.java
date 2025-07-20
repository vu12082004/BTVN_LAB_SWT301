package com.example.demo.automation.register;

import com.example.demo.automation.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage extends BasePage {
    private By firstNameField = By.id("firstName");
    private By lastNameField = By.id("lastName");
    private By emailField = By.id("userEmail");
    private By genderMaleRadio = By.xpath("//label[text()='Male']");
    private By mobileField = By.id("userNumber");
    private By submitBtn = By.id("submit");
    private By successMsg = By.cssSelector(".modal-content"); // Modal hiện sau khi submit thành công

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    public void navigate() {
        navigateTo("https://demoqa.com/automation-practice-form");
    }

    public void register(String firstName, String lastName, String email, String mobile) {
        type(firstNameField, firstName);
        type(lastNameField, lastName);
        type(emailField, email);
        click(genderMaleRadio); // Chọn Male (mặc định)
        type(mobileField, mobile);
        click(submitBtn); // Submit form
    }

    public By getSuccessLocator() {
        return successMsg; // "Thanks for submitting the form"
    }

    // Trang demoqa KHÔNG có errorMsg cố định khi thiếu field.
    // Bạn có thể mở rộng: kiểm tra thuộc tính CSS border nếu cần kiểm thử negative case.
    public By getErrorLocator() {
        // Trả về null hoặc có thể để trống vì không dùng tới.
        return By.id("some-fake-error"); // Không dùng tới
    }
}
