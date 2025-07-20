package com.example.demo.automation.tourify;

import com.example.demo.automation.base.BaseTest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tour Search Tests")
public class SearchTourTest extends BaseTest {
    static SearchPage searchPage;

    @BeforeAll
    static void initPage() {
        searchPage = new SearchPage(driver);
    }

    @ParameterizedTest(name = "[{index}] place={0}, dur={1}, type={2}, people={3} => expectResults={4}")
    @CsvFileSource(resources = "/search-data.csv", numLinesToSkip = 1)
    void testSearchTour(String place,
                        String duration,
                        String type,
                        String people,
                        String expectResults) {
        // Navigate & perform search
        searchPage.navigate();
        searchPage.setPlace(place == null ? "" : place.trim());
        searchPage.setDuration(duration == null ? "" : duration.trim());
        searchPage.setType(type == null ? "" : type.trim());
        searchPage.setPeople(people == null ? "" : people.trim());
        searchPage.submitSearch();

        boolean hasResults = searchPage.countResults() > 0;
        boolean expect = Boolean.parseBoolean(expectResults);
        if (expect) {
            assertTrue(hasResults,
                    "Expected at least one tour, but found none for [" +
                            place + ", " + duration + ", " + type + ", " + people + "]");
        } else {
            assertFalse(hasResults,
                    "Expected no tours, but found some for [" +
                            place + ", " + duration + ", " + type + ", " + people + "]");
        }
    }
}
