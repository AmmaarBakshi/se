package com.stqa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InventoryPage {

    private WebDriver driver;

    // ===== Locators =====
    private By pageTitle = By.className("title");
    private By cartBadge = By.className("shopping_cart_badge");
    private By menuButton = By.id("react-burger-menu-btn");
    private By logoutLink = By.id("logout_sidebar_link");
    private By firstProduct = By.cssSelector(".inventory_item_name");

    // ===== Constructor =====
    public InventoryPage(WebDriver driver) {
        this.driver = driver;
    }

    // ===== Actions =====
    public String getPageTitle() {
        return driver.findElement(pageTitle).getText();
    }

    public boolean isLoaded() {
        return driver.getCurrentUrl().contains("inventory.html");
    }

    public String getFirstProductName() {
        return driver.findElement(firstProduct).getText();
    }

    public void logout() {
        driver.findElement(menuButton).click();
        try { Thread.sleep(1000); } catch (InterruptedException e) {}
        driver.findElement(logoutLink).click();
    }
}
