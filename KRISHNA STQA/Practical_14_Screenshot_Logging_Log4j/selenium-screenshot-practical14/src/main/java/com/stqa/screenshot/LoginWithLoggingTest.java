package com.stqa.screenshot;

import java.time.Duration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import com.stqa.util.ScreenshotUtil;

public class LoginWithLoggingTest {

    private static final Logger logger =
            LogManager.getLogger(LoginWithLoggingTest.class);

    public static void main(String[] args) {

        logger.info("========== Starting Login Test ==========");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        try {
            // ----- Step 1: Open the page -----
            logger.info("Navigating to SauceDemo login page");
            driver.get("https://www.saucedemo.com/");
            logger.info("Page loaded successfully");

            // ----- Step 2: Enter credentials -----
            logger.info("Entering username");
            driver.findElement(By.id("user-name")).sendKeys("standard_user");

            logger.info("Entering password");
            driver.findElement(By.id("password")).sendKeys("secret_sauce");

            // ----- Step 3: Click login -----
            logger.info("Clicking the Login button");
            driver.findElement(By.id("login-button")).click();

            Thread.sleep(2000);

            // ----- Step 4: Validate the login -----
            WebElement titleElement = driver.findElement(By.className("title"));
            String title = titleElement.getText();

            if (title.equals("Products")) {
                logger.info("Assertion 1: Page title verified as 'Products'");
            } else {
                logger.error("Assertion 1 FAILED: Expected 'Products', found '" + title + "'");
                String path = ScreenshotUtil.captureScreenshot(driver, "title_mismatch");
                logger.error("Screenshot saved at: " + path);
            }

            // ----- Step 5: Deliberate failure to demonstrate screenshot on failure -----
            logger.info("Testing a deliberately failing assertion to demonstrate screenshots");
            WebElement incorrectElement = null;
            try {
                // This element does not exist on the page - findElement will fail
                incorrectElement = driver.findElement(By.id("non_existent_element"));
                logger.info("Element found: " + incorrectElement.getText());
            } catch (Exception ex) {
                logger.error("Expected failure: " + ex.getMessage());
                String path = ScreenshotUtil.captureScreenshot(driver, "assertion_failure");
                logger.error("Screenshot saved at: " + path);
            }

            logger.info("Login test completed with mixed results");

        } catch (Exception e) {
            logger.error("Unhandled exception during test", e);
            String path = ScreenshotUtil.captureScreenshot(driver, "unhandled_exception");
            logger.error("Screenshot saved at: " + path);
        } finally {
            driver.quit();
            logger.info("Browser closed");
            logger.info("========== Test Run Finished ==========");
        }
    }
}
