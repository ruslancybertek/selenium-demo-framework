package com.demo.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 * Central WebDriver creation. WebDriverManager downloads the matching
 * ChromeDriver automatically — no manual driver binaries in the repo.
 * Headless mode is on by default (override with -Dheadless=false).
 */
public class DriverFactory {

    private DriverFactory() {
    }

    public static WebDriver createDriver() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        if (Boolean.parseBoolean(System.getProperty("headless", "true"))) {
            options.addArguments("--headless=new");
        }
        options.addArguments("--no-sandbox", "--disable-dev-shm-usage", "--window-size=1920,1080");
        return new ChromeDriver(options);
    }
}
