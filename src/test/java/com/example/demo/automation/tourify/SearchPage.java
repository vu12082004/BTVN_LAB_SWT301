package com.example.demo.automation.tourify;

import com.example.demo.automation.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SearchPage extends BasePage {
    // Locators cho form search
    private final By placeInput     = By.cssSelector("input[name='placeName']");
    private final By durationSelect = By.cssSelector("select[name='duration']");
    private final By typeSelect     = By.cssSelector("select[name='categoryName']");
    private final By peopleInput    = By.cssSelector("input[name='touristNumberAssigned']");
    private final By searchButton   = By.cssSelector("button.search-btn");

    // Locator cho kết quả: mỗi tour card có class .tour-luxury-card
    private final By resultsCards   = By.cssSelector(".tour-luxury-card");

    public SearchPage(WebDriver driver) {
        super(driver);
    }

    /** Mở trang Search Tour */
    public void navigate() {
        driver.get("http://localhost:8080/tourify/landing");
        // đảm bảo form đã load
        waitForVisibility(placeInput, 5);
    }

    public void setPlace(String place) {
        WebElement el = waitForVisibility(placeInput, 5);
        el.clear();
        if (place != null && !place.isEmpty()) {
            el.sendKeys(place);
        }
    }

    // in SearchPage.java

    public void setDuration(String days) {
        // “Any” (empty) durations are rendered as the first <option value="">
        if (days != null && !days.equals("Any")) {
            selectByValue(durationSelect, days);
        }
    }

    public void setType(String category) {
        // skip “Any” or unknown categories
        if (category != null && !category.equals("Any")) {
            selectByValue(typeSelect, category);
        }
    }

    public void setPeople(String count) {
        WebElement el = waitForVisibility(peopleInput,5);
        el.clear();
        if (count != null && !count.isEmpty()) {
            el.sendKeys(count);
        }
    }


    public void submitSearch() {
        click(searchButton);
        // chờ kết quả xuất hiện hoặc message no-tour
        wait.until(driver ->
                !driver.findElements(resultsCards).isEmpty() ||
                        driver.findElements(By.id("noTourMsg")).stream()
                                .anyMatch(e -> e.isDisplayed())
        );
    }

    /** Trả về số lượng card tìm được */
    public int countResults() {
        List<WebElement> cards = driver.findElements(resultsCards);
        return cards.size();
    }
}
