package com.demo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
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
        // SauceDemo data-test ids: add-to-cart-<slug> flips to remove-<slug> once added
        String slug = productName.toLowerCase().replaceAll("[^a-z0-9]+", "-");
        By addButton = By.cssSelector("[data-test='add-to-cart-" + slug + "']");
        By removeButton = By.cssSelector("[data-test='remove-" + slug + "']");

        // Click and verify it registered; retry once if the click missed a re-render
        for (int attempt = 0; attempt < 2; attempt++) {
            wait.until(ExpectedConditions.elementToBeClickable(addButton)).click();
            try {
                wait.until(ExpectedConditions.visibilityOfElementLocated(removeButton));
                return;
            } catch (TimeoutException e) {
                // click didn't register — retry
            }
        }
        throw new TimeoutException("Add to cart did not register for: " + productName);
    }

    public String getCartCount() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(cartBadge)).getText();
    }
}
