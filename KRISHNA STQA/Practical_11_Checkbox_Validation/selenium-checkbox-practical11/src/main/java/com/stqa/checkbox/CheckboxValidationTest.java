package com.stqa.checkbox;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class CheckboxValidationTest {

    static final String WEB_URL = "http://localhost:8000/checkbox_page.html";

    public static void main(String[] args) throws InterruptedException {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        try {
            driver.get(WEB_URL);
            System.out.println("Opened: " + WEB_URL);
            System.out.println();

            // Locate all checkboxes on the page
            List<WebElement> checkboxes = driver.findElements(
                    By.cssSelector("input[type='checkbox']"));

            // ----- Initial count -----
            int total = checkboxes.size();
            int checked = 0, unchecked = 0;
            for (WebElement box : checkboxes) {
                if (box.isSelected()) checked++;
                else                  unchecked++;
            }

            System.out.println("=== INITIAL STATE ===");
            System.out.println("Total checkboxes : " + total);
            System.out.println("Checked          : " + checked);
            System.out.println("Unchecked        : " + unchecked);
            System.out.println();

            // Print each checkbox with its current state
            System.out.println("Individual checkbox states:");
            for (WebElement box : checkboxes) {
                String id = box.getAttribute("id");
                System.out.println(" id=" + id + " selected=" + box.isSelected());
            }
            System.out.println();

            // ----- Operation 1: Check all unchecked boxes -----
            System.out.println("=== OPERATION 1: Check all unchecked boxes ===");
            for (WebElement box : checkboxes) {
                if (!box.isSelected()) {
                    box.click();
                    Thread.sleep(300);
                }
            }
            int checkedAfterOp1 = 0;
            for (WebElement box : checkboxes) {
                if (box.isSelected()) checkedAfterOp1++;
            }
            System.out.println("Checked after operation : " + checkedAfterOp1 + " / " + total);
            System.out.println("Validation              : " + (checkedAfterOp1 == total ? "PASS" : "FAIL"));
            System.out.println();

            // ----- Operation 2: Uncheck all boxes -----
            System.out.println("=== OPERATION 2: Uncheck all boxes ===");
            for (WebElement box : checkboxes) {
                if (box.isSelected()) {
                    box.click();
                    Thread.sleep(300);
                }
            }
            int checkedAfterOp2 = 0;
            for (WebElement box : checkboxes) {
                if (box.isSelected()) checkedAfterOp2++;
            }
            System.out.println("Checked after operation : " + checkedAfterOp2 + " / " + total);
            System.out.println("Validation              : " + (checkedAfterOp2 == 0 ? "PASS" : "FAIL"));
            System.out.println();

            // ----- Operation 3: Dynamically select only specific boxes -----
            System.out.println("=== OPERATION 3: Select only Java and SQL ===");
            String[] targets = {"java", "sql"};
            for (String id : targets) {
                WebElement box = driver.findElement(By.id(id));
                if (!box.isSelected()) box.click();
                Thread.sleep(300);
            }

            int checkedAfterOp3 = 0;
            for (WebElement box : checkboxes) {
                if (box.isSelected()) checkedAfterOp3++;
            }
            System.out.println("Checked after operation : " + checkedAfterOp3 + " / " + total);
            System.out.println("Validation              : " + (checkedAfterOp3 == 2 ? "PASS" : "FAIL"));
            System.out.println();

            // ----- Final summary -----
            System.out.println("=========================================");
            System.out.println("CHECKBOX VERIFICATION SUMMARY");
            System.out.println("=========================================");
            System.out.println("Total checkboxes            : " + total);
            System.out.println("Initially checked           : " + checked);
            System.out.println("Initially unchecked         : " + unchecked);
            System.out.println("After 'Check All' operation : " + checkedAfterOp1);
            System.out.println("After 'Uncheck All'         : " + checkedAfterOp2);
            System.out.println("After selective selection   : " + checkedAfterOp3);
            System.out.println("=========================================");

        } catch (Exception e) {
            System.out.println("Test error: " + e.getMessage());
        } finally {
            driver.quit();
            System.out.println("\nBrowser closed. Test complete.");
        }
    }
}
