package com.stqa.login;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SauceDemoLoginTest {

    public static void main(String[] args) {

        java.util.logging.Logger.getLogger("org.openqa.selenium.devtools.CdpVersionFinder")
                .setLevel(java.util.logging.Level.OFF);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        WebDriver driver = new ChromeDriver(options);

        try {
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

            // Step 1: Open SauceDemo
            driver.get("https://www.saucedemo.com/");
            System.out.println("Opened SauceDemo login page.");

            // Step 2: Enter username
            WebElement usernameField = driver.findElement(By.id("user-name"));
            usernameField.clear();
            usernameField.sendKeys("standard_user");

            // Step 3: Enter password
            WebElement passwordField = driver.findElement(By.id("password"));
            passwordField.clear();
            passwordField.sendKeys("secret_sauce");

            // Step 4: Click Login
            WebElement loginButton = driver.findElement(By.id("login-button"));
            loginButton.click();
            System.out.println("Clicked Login button.");

            Thread.sleep(2000);

            // ====================================================
            // ASSERTION 1: URL Validation
            // ====================================================
            String actualUrl = driver.getCurrentUrl();
            if (actualUrl.contains("inventory.html")) {
                System.out.println("ASSERTION 1 (URL)   : PASS -> " + actualUrl);
            } else {
                System.out.println("ASSERTION 1 (URL)   : FAIL -> " + actualUrl);
            }

            // ====================================================
            // ASSERTION 2: Title Validation
            // ====================================================
            WebElement productsTitle = driver.findElement(By.className("title"));
            String actualTitle = productsTitle.getText();
            if (actualTitle.equals("Products")) {
                System.out.println("ASSERTION 2 (Title) : PASS -> " + actualTitle);
            } else {
                System.out.println("ASSERTION 2 (Title) : FAIL -> " + actualTitle);
            }

            // ====================================================
            // ASSERTION 3: Logout Link Visibility Check
            // ====================================================
            // Click hamburger menu using JavaScript (bypasses all click interception)
            WebElement menuButton = driver.findElement(By.id("react-burger-menu-btn"));
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", menuButton);
            System.out.println("Clicked hamburger menu.");

            // Wait for the logout link to be present in the DOM
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement logoutLink = wait.until(
                    ExpectedConditions.presenceOfElementLocated(By.id("logout_sidebar_link")));

            if (logoutLink.isDisplayed()) {
                System.out.println("ASSERTION 3 (Logout)      : PASS -> Logout link is visible");
            } else {
                System.out.println("ASSERTION 3 (Logout)      : FAIL -> Logout link not visible");
            }

            // ====================================================
            // BONUS: Perform actual logout via JavaScript click
            // ====================================================
            js.executeScript("arguments[0].click();", logoutLink);
            Thread.sleep(2000);

            String afterLogoutUrl = driver.getCurrentUrl();
            if (afterLogoutUrl.equals("https://www.saucedemo.com/")) {
                System.out.println("BONUS (After Logout) : PASS -> Back on login page");
            } else {
                System.out.println("BONUS (After Logout) : FAIL -> " + afterLogoutUrl);
            }

        } catch (Exception e) {
            System.out.println("Test encountered an error: " + e.getMessage());
        } finally {
            driver.quit();
            System.out.println("\nBrowser closed. Test complete.");
        }
    }
}
