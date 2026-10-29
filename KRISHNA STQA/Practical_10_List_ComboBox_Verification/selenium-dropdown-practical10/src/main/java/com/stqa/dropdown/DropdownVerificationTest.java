package com.stqa.dropdown;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

public class DropdownVerificationTest {

    static final String WEB_URL = "https://the-internet.herokuapp.com/dropdown";

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

            // Locate the dropdown and wrap it in a Select object
            WebElement dropdownElement = driver.findElement(By.id("dropdown"));
            Select dropdown = new Select(dropdownElement);

            // ----- 1. CHECK THE TYPE OF DROPDOWN -----
            boolean isMulti = dropdown.isMultiple();
            System.out.println("Is this a multi-select dropdown? : " + isMulti);

            // ----- 2. COUNT THE TOTAL ITEMS IN THE LIST -----
            List<WebElement> options2 = dropdown.getOptions();
            int totalItems = options2.size();
            System.out.println("Total items in the dropdown : " + totalItems);
            System.out.println();

            // ----- 3. PRINT ALL OPTIONS IN ORDER -----
            System.out.println("All options in the dropdown:");
            for (int i = 0; i < totalItems; i++) {
                String text = options2.get(i).getText();
                String value = options2.get(i).getAttribute("value");
                System.out.println(" [" + i + "] Text='" + text + "' | value='" + value + "'");
            }
            System.out.println();

            // ----- 4. VALIDATE THE DEFAULT SELECTED VALUE -----
            String defaultText = dropdown.getFirstSelectedOption().getText();
            System.out.println("Default selected option : '" + defaultText + "'");

            // The first option on this page is a placeholder with empty value
            String expectedDefault = "Please select an option";
            System.out.println("Default validation      : " +
                    (defaultText.equals(expectedDefault) ? "PASS" : "FAIL"));
            System.out.println();

            // ----- 5. SELECT BY VISIBLE TEXT -----
            System.out.println("Testing selectByVisibleText('Option 1') ...");
            dropdown.selectByVisibleText("Option 1");
            Thread.sleep(1000);
            String after1 = dropdown.getFirstSelectedOption().getText();
            System.out.println("Currently selected : '" + after1 + "'");
            System.out.println("Result             : " + (after1.equals("Option 1") ? "PASS" : "FAIL"));
            System.out.println();

            // ----- 6. SELECT BY INDEX -----
            System.out.println("Testing selectByIndex(2) ...");
            dropdown.selectByIndex(2);
            Thread.sleep(1000);
            String after2 = dropdown.getFirstSelectedOption().getText();
            System.out.println("Currently selected : '" + after2 + "'");
            System.out.println("Result             : " + (after2.equals("Option 2") ? "PASS" : "FAIL"));
            System.out.println();

            // ----- 7. SELECT BY VALUE -----
            System.out.println("Testing selectByValue('1') ...");
            dropdown.selectByValue("1");
            Thread.sleep(1000);
            String after3 = dropdown.getFirstSelectedOption().getText();
            System.out.println("Currently selected : '" + after3 + "'");
            System.out.println("Result             : " + (after3.equals("Option 1") ? "PASS" : "FAIL"));
            System.out.println();

            // ----- SUMMARY -----
            System.out.println("=========================================");
            System.out.println("DROPDOWN VERIFICATION SUMMARY");
            System.out.println("=========================================");
            System.out.println("Dropdown location    : id=dropdown");
            System.out.println("Total items          : " + totalItems);
            System.out.println("Default selection    : " + defaultText);
            System.out.println("Multi-select allowed : " + isMulti);
            System.out.println("=========================================");

        } catch (Exception e) {
            System.out.println("Test error: " + e.getMessage());
        } finally {
            driver.quit();
            System.out.println("\nBrowser closed. Test complete.");
        }
    }
}
