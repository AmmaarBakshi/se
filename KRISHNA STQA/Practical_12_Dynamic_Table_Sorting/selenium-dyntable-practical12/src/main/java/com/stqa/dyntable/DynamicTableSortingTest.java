package com.stqa.dyntable;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DynamicTableSortingTest {

    static final String WEB_URL = "http://localhost:8000/sortable_table.html";

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

            // =========================================================
            // STEP 1: Read the initial table state
            // =========================================================
            System.out.println("=== STEP 1: INITIAL TABLE STATE ===");
            printTable(driver);
            System.out.println();

            // =========================================================
            // STEP 2: Validate sorting by Name (column index 1)
            // =========================================================
            System.out.println("=== STEP 2: SORT BY NAME (ASCENDING) ===");
            driver.findElement(By.xpath("//th[text()='Name']")).click();
            Thread.sleep(800);
            List<String> namesAfterFirstClick = readColumn(driver, 1);
            System.out.println("Names after first click : " + namesAfterFirstClick);
            System.out.println("Is ascending?           : " +
                    (isSortedAscending(namesAfterFirstClick) ? "PASS" : "FAIL"));
            System.out.println();

            System.out.println("=== STEP 2b: SORT BY NAME (DESCENDING) ===");
            driver.findElement(By.xpath("//th[text()='Name']")).click();
            Thread.sleep(800);
            List<String> namesAfterSecondClick = readColumn(driver, 1);
            System.out.println("Names after second click: " + namesAfterSecondClick);
            System.out.println("Is descending?          : " +
                    (isSortedDescending(namesAfterSecondClick) ? "PASS" : "FAIL"));
            System.out.println();

            // =========================================================
            // STEP 3: Validate sorting by Salary (numeric column, index 4)
            // =========================================================
            System.out.println("=== STEP 3: SORT BY SALARY (ASCENDING) ===");
            driver.findElement(By.xpath("//th[text()='Salary']")).click();
            Thread.sleep(800);
            List<Integer> salaries = readNumericColumn(driver, 4);
            System.out.println("Salaries after sort   : " + salaries);
            System.out.println("Is ascending numeric? : " +
                    (isNumericAscending(salaries) ? "PASS" : "FAIL"));
            System.out.println();

            // =========================================================
            // STEP 4: Validate filtering by name
            // =========================================================
            System.out.println("=== STEP 4: FILTER BY NAME 'Alice' ===");
            WebElement filterBox = driver.findElement(By.id("filterBox"));
            filterBox.clear();
            filterBox.sendKeys("Alice");
            Thread.sleep(800);

            List<WebElement> visibleRows = getVisibleRows(driver);
            System.out.println("Rows visible after filter: " + visibleRows.size());
            for (WebElement row : visibleRows) {
                List<WebElement> cells = row.findElements(By.tagName("td"));
                System.out.println(" " + cells.get(0).getText() + " | "
                        + cells.get(1).getText() + " | "
                        + cells.get(2).getText() + " | "
                        + cells.get(3).getText() + " | "
                        + cells.get(4).getText());
            }

            boolean filterCorrect = visibleRows.size() == 1
                    && visibleRows.get(0).findElements(By.tagName("td"))
                            .get(1).getText().equals("Alice");
            System.out.println("Filter validation : " + (filterCorrect ? "PASS" : "FAIL"));
            System.out.println();

            // =========================================================
            // STEP 5: Clear filter and verify all rows return
            // =========================================================
            System.out.println("=== STEP 5: CLEAR FILTER ===");
            filterBox.clear();
            Thread.sleep(800);
            int rowsAfterClear = getVisibleRows(driver).size();
            System.out.println("Rows visible after clear: " + rowsAfterClear);
            System.out.println("All rows returned?      : " + (rowsAfterClear == 5 ? "PASS" : "FAIL"));
            System.out.println();

            // =========================================================
            // Summary
            // =========================================================
            System.out.println("=========================================");
            System.out.println("DYNAMIC TABLE VERIFICATION SUMMARY");
            System.out.println("=========================================");
            System.out.println("Sort by Name (asc)    : " + (isSortedAscending(namesAfterFirstClick) ? "PASS" : "FAIL"));
            System.out.println("Sort by Name (desc)   : " + (isSortedDescending(namesAfterSecondClick) ? "PASS" : "FAIL"));
            System.out.println("Sort by Salary (num)  : " + (isNumericAscending(salaries) ? "PASS" : "FAIL"));
            System.out.println("Filter by 'Alice'     : " + (filterCorrect ? "PASS" : "FAIL"));
            System.out.println("Clear filter restores : " + (rowsAfterClear == 5 ? "PASS" : "FAIL"));
            System.out.println("=========================================");

        } catch (Exception e) {
            System.out.println("Test error: " + e.getMessage());
        } finally {
            driver.quit();
            System.out.println("\nBrowser closed. Test complete.");
        }
    }

    // ================== Helper methods ==================

    static void printTable(WebDriver driver) {
        List<WebElement> rows = driver.findElements(By.cssSelector("#empTable tbody tr"));
        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            StringBuilder sb = new StringBuilder(" ");
            for (WebElement cell : cells) sb.append(cell.getText()).append(" | ");
            System.out.println(sb.toString());
        }
    }

    static List<String> readColumn(WebDriver driver, int colIndex) {
        List<WebElement> rows = driver.findElements(By.cssSelector("#empTable tbody tr"));
        List<String> values = new ArrayList<>();
        for (WebElement row : rows) {
            values.add(row.findElements(By.tagName("td")).get(colIndex).getText());
        }
        return values;
    }

    static List<Integer> readNumericColumn(WebDriver driver, int colIndex) {
        List<WebElement> rows = driver.findElements(By.cssSelector("#empTable tbody tr"));
        List<Integer> values = new ArrayList<>();
        for (WebElement row : rows) {
            values.add(Integer.parseInt(
                    row.findElements(By.tagName("td")).get(colIndex).getText()));
        }
        return values;
    }

    static List<WebElement> getVisibleRows(WebDriver driver) {
        List<WebElement> allRows = driver.findElements(By.cssSelector("#empTable tbody tr"));
        List<WebElement> visible = new ArrayList<>();
        for (WebElement row : allRows) {
            if (row.isDisplayed()) visible.add(row);
        }
        return visible;
    }

    static boolean isSortedAscending(List<String> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i).compareToIgnoreCase(list.get(i + 1)) > 0) return false;
        }
        return true;
    }

    static boolean isSortedDescending(List<String> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i).compareToIgnoreCase(list.get(i + 1)) < 0) return false;
        }
        return true;
    }

    static boolean isNumericAscending(List<Integer> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i) > list.get(i + 1)) return false;
        }
        return true;
    }
}
