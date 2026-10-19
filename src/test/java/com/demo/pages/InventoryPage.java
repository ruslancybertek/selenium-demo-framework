package com.demo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Page Object for the SauceDemo inventory (products) page.
 */
public class InventoryPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By title = By.className("title");
    private final By cartBadge = By.className("shopping_cart_badge");

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public String getTitle() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(title)).getText();
    }

    public void addToCart(String productName) {
        // SauceDemo data-test ids look like: add-to-cart-sauce-labs-backpack
        String slug = productName.toLowerCase().replaceAll("[^a-z0-9]+", "-");
        driver.findElement(By.cssSelector("[data-test='add-to-cart-" + slug + "']")).click();
    }

    public String getCartCount() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(cartBadge)).getText();
    }
}
