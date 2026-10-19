package com.demo.tests;

import com.demo.base.BaseTest;
import com.demo.pages.InventoryPage;
import com.demo.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CartTest extends BaseTest {

    @BeforeMethod
    public void login() {
        new LoginPage(driver()).login("standard_user", "secret_sauce");
    }

    @Test(description = "Adding a product updates the cart badge")
    public void addSingleProductUpdatesCart() {
        InventoryPage inventory = new InventoryPage(driver());

        inventory.addToCart("Sauce Labs Backpack");

        Assert.assertEquals(inventory.getCartCount(), "1",
                "Cart badge should show 1");
    }

    @Test(description = "Adding two products updates the cart badge to 2")
    public void addTwoProductsUpdatesCart() {
        InventoryPage inventory = new InventoryPage(driver());

        inventory.addToCart("Sauce Labs Backpack");
        inventory.addToCart("Sauce Labs Bike Light");

        Assert.assertEquals(inventory.getCartCount(), "2",
                "Cart badge should show 2");
    }
}
