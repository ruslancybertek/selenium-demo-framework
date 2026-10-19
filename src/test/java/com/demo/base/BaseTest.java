package com.demo.base;

import com.demo.utils.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

/**
 * Every UI test gets its own WebDriver, stored in a ThreadLocal so tests
 * can safely run in parallel (see testng.xml) without sharing a browser.
 */
public class BaseTest {

    private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();
    protected static final String BASE_URL = "https://www.saucedemo.com";

    /** The WebDriver for the current test thread. */
    protected WebDriver driver() {
        return DRIVER.get();
    }

    @BeforeMethod
    public void setUp() {
        DRIVER.set(DriverFactory.createDriver());
        driver().get(BASE_URL);
    }

    @AfterMethod
    public void tearDown() {
        WebDriver driver = DRIVER.get();
        if (driver != null) {
            driver.quit();
            DRIVER.remove();
        }
    }
}
