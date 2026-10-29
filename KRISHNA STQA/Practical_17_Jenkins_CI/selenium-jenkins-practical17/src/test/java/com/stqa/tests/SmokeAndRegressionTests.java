package com.stqa.tests;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.stqa.base.BaseTest;
import com.stqa.pages.InventoryPage;
import com.stqa.pages.LoginPage;

public class SmokeAndRegressionTests extends BaseTest {

    private static final String URL = "https://www.saucedemo.com/";

    @BeforeClass
    public void beforeAll() {
        System.out.println(">>> @BeforeClass : Runs ONCE before all tests in this class");
    }

    @AfterClass
    public void afterAll() {
        System.out.println(">>> @AfterClass : Runs ONCE after all tests in this class");
    }

    // ---------- SMOKE TESTS ----------

    @Test(priority = 1, groups = {"smoke"},
            description = "Verify the login page loads successfully")
    public void smokeTest_LoginPageLoads() {
        driver.get(URL);
        Assert.assertTrue(driver.getTitle().contains("Swag Labs"),
                "Login page title should contain 'Swag Labs'");
        System.out.println("smokeTest_LoginPageLoads : PASS");
    }

    @Test(priority = 2, groups = {"smoke"},
            description = "Verify valid login works")
    public void smokeTest_ValidLogin() {
        driver.get(URL);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        InventoryPage inventoryPage = new InventoryPage(driver);
        Assert.assertTrue(inventoryPage.isLoaded(),
                "Should land on inventory page after valid login");
        System.out.println("smokeTest_ValidLogin : PASS");
    }

    // ---------- REGRESSION TESTS ----------

    @Test(priority = 3, groups = {"regression"},
            description = "Verify invalid credentials are rejected")
    public void regressionTest_InvalidLogin() {
        driver.get(URL);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("wrong_user", "wrong_pass");
        Assert.assertTrue(loginPage.isErrorDisplayed(),
                "Error should appear for invalid credentials");
        System.out.println("regressionTest_InvalidLogin : PASS");
    }

    @Test(priority = 4, groups = {"regression"},
            description = "Verify empty fields are rejected")
    public void regressionTest_EmptyFields() {
        driver.get(URL);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickLogin();
        Assert.assertTrue(loginPage.getErrorMessage().contains("Username is required"),
                "Error should mention username required");
        System.out.println("regressionTest_EmptyFields : PASS");
    }

    @Test(priority = 5, groups = {"regression"},
            description = "Verify first product name is displayed")
    public void regressionTest_FirstProductVisible() {
        driver.get(URL);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        InventoryPage inventoryPage = new InventoryPage(driver);
        String firstProduct = inventoryPage.getFirstProductName();
        Assert.assertFalse(firstProduct.isEmpty(),
                "First product name should not be empty");
        System.out.println("regressionTest_FirstProductVisible : PASS");
    }
}
