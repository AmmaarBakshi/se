package com.stqa.grid;

import java.net.URL;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class GridCrossBrowserTest {

    private static final String GRID_URL = "http://localhost:4444";
    private static final String TEST_URL = "https://example.com/";

    public static void main(String[] args) {
        System.out.println("Starting Cross-Browser Test through Selenium Grid...\n");
        runTest("Chrome", new ChromeOptions());
        runTest("Firefox", new FirefoxOptions());
    }

    private static void runTest(String browserName, MutableCapabilities options) {
        WebDriver driver = null;
        try {
            options.setCapability("se:name", "STQA Practical 4 - " + browserName);
            driver = new RemoteWebDriver(new URL(GRID_URL), options);
            driver.get(TEST_URL);

            String title = driver.getTitle();
            String currentUrl = driver.getCurrentUrl();

            System.out.println("----------------------------------------");
            System.out.println("Browser : " + browserName);
            System.out.println("Title : " + title);
            System.out.println("URL : " + currentUrl);

            if (title.equals("Example Domain")) {
                System.out.println("Result : PASS");
            } else {
                System.out.println("Result : FAIL");
            }
        } catch (Exception e) {
            System.out.println("----------------------------------------");
            System.out.println("Browser : " + browserName);
            System.out.println("Result : FAIL");
            System.out.println("Error : " + e.getMessage());
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
    }
}
