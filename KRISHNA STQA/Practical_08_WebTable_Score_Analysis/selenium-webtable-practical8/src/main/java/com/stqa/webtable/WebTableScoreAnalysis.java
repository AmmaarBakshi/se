package com.stqa.webtable;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebTableScoreAnalysis {

    // Update this URL if your Python server is on a different port
    static final String WEB_URL = "http://localhost:8000/student_table.html";

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

            // Locate all rows inside the tbody (skips the header row)
            List<WebElement> rows = driver.findElements(
                    By.cssSelector("#studentTable tbody tr"));

            int totalStudents = rows.size();
            int passedCount = 0;

            System.out.println("Roll | Name | Phy | Chem | Maths | 60+ in at least one?");
            System.out.println("---------------------------------------------------------------");

            for (WebElement row : rows) {
                List<WebElement> cells = row.findElements(By.tagName("td"));

                String roll = cells.get(0).getText();
                String name = cells.get(1).getText();
                int physics = Integer.parseInt(cells.get(2).getText());
                int chem = Integer.parseInt(cells.get(3).getText());
                int maths = Integer.parseInt(cells.get(4).getText());

                boolean scored60 = (physics >= 60 || chem >= 60 || maths >= 60);
                if (scored60) passedCount++;

                System.out.printf("%-4s | %-7s | %-3d | %-4d | %-5d | %s%n",
                        roll, name, physics, chem, maths, scored60 ? "YES" : "NO");
            }

            double percentage = (passedCount * 100.0) / totalStudents;

            System.out.println();
            System.out.println("=================================================");
            System.out.println("SCORE ANALYSIS SUMMARY");
            System.out.println("=================================================");
            System.out.println("Total students                     : " + totalStudents);
            System.out.println("Scored 60+ in at least one subject : " + passedCount);
            System.out.println("Did not score 60 in any subject    : " + (totalStudents - passedCount));
            System.out.println("Percentage of students with 60+    : " + percentage + " %");
            System.out.println("=================================================");

        } catch (Exception e) {
            System.out.println("Test error: " + e.getMessage());
        } finally {
            driver.quit();
            System.out.println("\nBrowser closed. Test complete.");
        }
    }
}
