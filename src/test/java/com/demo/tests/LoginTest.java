package com.demo.tests;

import com.demo.base.BaseTest;
import com.demo.pages.InventoryPage;
import com.demo.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test(description = "Standard user logs in and lands on the inventory page")
    public void standardUserCanLogin() {
        InventoryPage inventory = new LoginPage(driver())
                .login("standard_user", "secret_sauce");

        Assert.assertEquals(inventory.getTitle(), "Products",
                "Inventory page should be displayed after login");
    }

    @Test(description = "Locked-out user sees an error message")
    public void lockedOutUserSeesError() {
        LoginPage login = new LoginPage(driver());
        login.login("locked_out_user", "secret_sauce");

        Assert.assertTrue(login.isErrorVisible(),
                "Error message should be visible for locked-out user");
    }
}
