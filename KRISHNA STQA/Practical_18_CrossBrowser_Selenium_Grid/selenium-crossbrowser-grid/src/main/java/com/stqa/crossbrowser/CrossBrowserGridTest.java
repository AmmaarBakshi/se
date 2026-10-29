package com.stqa.crossbrowser;

import java.net.URL;
import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class CrossBrowserGridTest {

    static final String GRID_URL = "http://localhost:4444";
    static final String TEST_URL = "https://www.saucedemo.com/";

    public static void main(String[] args) {
        System.out.println("===== CROSS-BROWSER GRID TEST =====\n");
        runTest("Chrome", new ChromeOptions());
        runTest("Firefox", new FirefoxOptions());
        runTest("Edge", new EdgeOptions());
        System.out.println("\n===== ALL BROWSER TESTS COMPLETED =====");
    }

    static void runTest(String browserName, MutableCapabilities options) {
        WebDriver driver = null;
        long startTime = System.currentTimeMillis();
        try {
            options.setCapability("se:name", "STQA CrossBrowser - " + browserName);
            driver = new RemoteWebDriver(new URL(GRID_URL), options);
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

            driver.get(TEST_URL);

            String title = driver.getTitle();
            String url = driver.getCurrentUrl();

            long elapsed = System.currentTimeMillis() - startTime;

            System.out.println("----------------------------------------");
            System.out.println("Browser  : " + browserName);
            System.out.println("Title    : " + title);
            System.out.println("URL      : " + url);
            System.out.println("Time (ms): " + elapsed);
            System.out.println("Result   : " + (title.contains("Swag Labs") ? "PASS" : "FAIL"));
        } catch (Exception e) {
            System.out.println("----------------------------------------");
            System.out.println("Browser : " + browserName);
            System.out.println("Result  : FAIL - " + e.getMessage());
        } finally {
            if (driver != null) driver.quit();
        }
    }
}
