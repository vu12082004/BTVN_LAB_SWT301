package com.example.demo.automation.booking;

import com.example.demo.automation.base.BaseTest;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BookingTest extends BaseTest {
    static BookingPage bookingPage;

    @BeforeAll
    static void setup() {
        bookingPage = new BookingPage(driver);
    }

    @ParameterizedTest(name = "Booking: {0} -> {1}, expect={2}")
    @CsvFileSource(resources = "/booking-data.csv", numLinesToSkip = 1)
    @Order(1)
    void testBookingCsv(String from, String to, String expected) {
        bookingPage.navigate();
        boolean enoughData = from != null && !from.isBlank() && to != null && !to.isBlank();

        boolean result = false;
        if (enoughData) {
            bookingPage.searchFlight(from, to);
            bookingPage.chooseFirstFlight();
            bookingPage.fillPassengerInfoAndBook();
            result = bookingPage.isBookingSuccess();
        }
        if ("success".equalsIgnoreCase(expected)) {
            assertTrue(result, "Expected booking success but it was not displayed!");
        } else {
            assertTrue(!result, "Expected failure but success modal appeared!");
        }
    }
}
