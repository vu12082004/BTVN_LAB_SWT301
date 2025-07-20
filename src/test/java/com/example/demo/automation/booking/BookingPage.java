package com.example.demo.automation.booking;

import com.example.demo.automation.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BookingPage extends BasePage {
    private By fromPort = By.name("fromPort");
    private By toPort = By.name("toPort");
    private By findFlightsBtn = By.cssSelector("input[type='submit']");
    private By chooseFirstFlightBtn = By.xpath("//table//tr[1]//td[1]//input[@type='submit']");
    private By nameField = By.id("inputName");
    private By addressField = By.id("address");
    private By cityField = By.id("city");
    private By stateField = By.id("state");
    private By zipCodeField = By.id("zipCode");
    private By cardNumberField = By.id("creditCardNumber");
    private By purchaseFlightBtn = By.cssSelector("input[type='submit']");
    private By successMsg = By.xpath("//*[contains(text(),'Thank you for your purchase today!')]");

    public BookingPage(WebDriver driver) {
        super(driver);
    }

    public void navigate() {
        driver.get("http://blazedemo.com/");
    }

    public void searchFlight(String from, String to) {
        selectDropdownByVisibleText(fromPort, from);
        selectDropdownByVisibleText(toPort, to);
        click(findFlightsBtn);
    }

    public void chooseFirstFlight() {
        click(chooseFirstFlightBtn);
    }

    public void fillPassengerInfoAndBook() {
        type(nameField, "Test User");
        type(addressField, "123 Demo St");
        type(cityField, "DemoCity");
        type(stateField, "DemoState");
        type(zipCodeField, "12345");
        type(cardNumberField, "4111111111111111");
        click(purchaseFlightBtn);
    }

    public boolean isBookingSuccess() {
        return isElementVisible(successMsg, 10);
    }
}
