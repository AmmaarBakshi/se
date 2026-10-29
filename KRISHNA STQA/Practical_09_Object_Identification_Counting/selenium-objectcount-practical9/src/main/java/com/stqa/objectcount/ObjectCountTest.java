package com.stqa.objectcount;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ObjectCountTest {

    // The website under test - The Internet (a stable automation demo site)
    static final String WEB_URL = "https://the-internet.herokuapp.com/";

    public static void main(String[] args) {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        try {
            driver.get(WEB_URL);
            System.out.println("Opened: " + WEB_URL);
            System.out.println();

            // Count different types of objects using findElements + size()
            List<WebElement> links = driver.findElements(By.tagName("a"));
            List<WebElement> images = driver.findElements(By.tagName("img"));
            List<WebElement> buttons = driver.findElements(By.tagName("button"));
            List<WebElement> inputs = driver.findElements(By.tagName("input"));
            List<WebElement> headings = driver.findElements(By.tagName("h2"));

            int totalObjects = links.size() + images.size()
                    + buttons.size() + inputs.size() + headings.size();

            System.out.println("=================== OBJECT COUNT SUMMARY ===================");
            System.out.println("Total objects found on the page : " + totalObjects);
            System.out.println("------------------------------------------------------------");
            System.out.println("Links (<a>)            : " + links.size());
            System.out.println("Images (<img>)         : " + images.size());
            System.out.println("Buttons (<button>)     : " + buttons.size());
            System.out.println("Input fields (<input>) : " + inputs.size());
            System.out.println("Headings (<h2>)        : " + headings.size());
            System.out.println("------------------------------------------------------------");

            // Show first few link texts as a sample
            System.out.println("\nFirst 5 link texts on the page:");
            int limit = Math.min(5, links.size());
            for (int i = 0; i < limit; i++) {
                String text = links.get(i).getText().trim();
                if (text.isEmpty()) text = "(no visible text)";
                System.out.println(" " + (i + 1) + ". " + text);
            }

            System.out.println("\nTotal links displayed as sample: " + limit + " of " + links.size());

        } catch (Exception e) {
            System.out.println("Test error: " + e.getMessage());
        } finally {
            driver.quit();
            System.out.println("\nBrowser closed. Test complete.");
        }
    }
}
