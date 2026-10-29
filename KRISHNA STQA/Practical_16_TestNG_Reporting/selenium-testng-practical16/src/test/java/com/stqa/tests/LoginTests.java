package com.stqa.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.stqa.base.BaseTest;
import com.stqa.pages.InventoryPage;
import com.stqa.pages.LoginPage;

public class LoginTests extends BaseTest {

    private static final String URL = "https://www.saucedemo.com/";

    @Test(priority = 1)
    public void testValidLogin() {
        System.out.println("[Test] Running: testValidLogin");
        driver.get(URL);

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        InventoryPage inventoryPage = new InventoryPage(driver);
        Assert.assertTrue(inventoryPage.isLoaded(),
                "User should land on inventory page after valid login");
        Assert.assertEquals(inventoryPage.getPageTitle(), "Products",
                "Page title should be 'Products'");
    }

    @Test(priority = 2)
    public void testInvalidLogin() {
        System.out.println("[Test] Running: testInvalidLogin");
        driver.get(URL);

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("wrong_user", "wrong_pass");

        Assert.assertTrue(loginPage.isErrorDisplayed(),
                "Error message should appear for invalid credentials");
        Assert.assertTrue(loginPage.getErrorMessage().contains("Username and password do not match"),
                "Error message should mention credential mismatch");
    }

    @Test(priority = 3)
    public void testEmptyFields() {
        System.out.println("[Test] Running: testEmptyFields");
        driver.get(URL);

        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickLogin();

        Assert.assertTrue(loginPage.isErrorDisplayed(),
                "Error message should appear when fields are empty");
        Assert.assertTrue(loginPage.getErrorMessage().contains("Username is required"),
                "Error should mention username is required");
    }
}
