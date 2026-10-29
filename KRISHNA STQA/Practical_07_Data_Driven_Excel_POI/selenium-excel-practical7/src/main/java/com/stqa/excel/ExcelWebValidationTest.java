package com.stqa.excel;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.time.Duration;
import java.util.List;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ExcelWebValidationTest {

    // Paths are relative to the project folder (Eclipse working directory).
    static final String INPUT_EXCEL  = "data/student_records.xlsx";
    static final String OUTPUT_EXCEL = "data/student_records_updated.xlsx";
    static final String WEB_URL      = "http://localhost:8000/student_table.html";

    public static void main(String[] args) throws Exception {

        System.out.println("===== STEP 1: READ EXCEL AND UPDATE RECORDS =====\n");

        FileInputStream fis = new FileInputStream(INPUT_EXCEL);
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheetAt(0);

        int lastRow = sheet.getLastRowNum();
        Row header = sheet.getRow(0);
        int lastCol = header.getLastCellNum();

        header.createCell(lastCol).setCellValue("Total");
        header.createCell(lastCol + 1).setCellValue("Result");

        int passCount = 0, failCount = 0;

        for (int i = 1; i <= lastRow; i++) {
            Row row = sheet.getRow(i);
            String name = row.getCell(1).getStringCellValue();
            int phy = (int) row.getCell(2).getNumericCellValue();
            int chem = (int) row.getCell(3).getNumericCellValue();
            int math = (int) row.getCell(4).getNumericCellValue();

            int total = phy + chem + math;
            String result = (phy >= 60 || chem >= 60 || math >= 60) ? "Pass" : "Fail";

            if (result.equals("Pass")) passCount++; else failCount++;

            row.createCell(lastCol).setCellValue(total);
            row.createCell(lastCol + 1).setCellValue(result);

            System.out.println("Row " + i + ": " + name
                    + " | Total=" + total + " | Result=" + result);
        }

        FileOutputStream fos = new FileOutputStream(OUTPUT_EXCEL);
        workbook.write(fos);
        fos.close();
        workbook.close();
        fis.close();

        System.out.println("\nExcel updated. Output: " + OUTPUT_EXCEL);
        System.out.println("Summary: Pass=" + passCount + " Fail=" + failCount);

        System.out.println("\n===== STEP 2: VALIDATE DATA ON WEB PAGE =====\n");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        try {
            driver.get(WEB_URL);
            System.out.println("Opened web page: " + WEB_URL);

            List<WebElement> rows = driver.findElements(
                    By.cssSelector("#studentTable tbody tr"));
            System.out.println("Rows found on page: " + rows.size());

            FileInputStream fis2 = new FileInputStream(OUTPUT_EXCEL);
            Workbook wb2 = new XSSFWorkbook(fis2);
            Sheet sheet2 = wb2.getSheetAt(0);

            int matches = 0;
            int mismatches = 0;

            for (int i = 0; i < rows.size(); i++) {
                List<WebElement> cells = rows.get(i).findElements(By.tagName("td"));

                String webRoll = cells.get(0).getText();
                String webName = cells.get(1).getText();
                String webTotal = cells.get(5).getText();
                String webResult = cells.get(6).getText();

                Row excelRow = sheet2.getRow(i + 1);
                String excelTotal = String.valueOf((int) excelRow.getCell(5).getNumericCellValue());
                String excelResult = excelRow.getCell(6).getStringCellValue();

                boolean match = excelTotal.equals(webTotal) && excelResult.equals(webResult);
                if (match) matches++; else mismatches++;

                System.out.println("Row " + webRoll + " | " + webName
                        + " | Web[Total=" + webTotal + ", Result=" + webResult + "]"
                        + " | Excel[Total=" + excelTotal + ", Result=" + excelResult + "]"
                        + " | " + (match ? "MATCH" : "MISMATCH"));
            }

            wb2.close();
            fis2.close();

            System.out.println("\n========================================");
            System.out.println("Verification Summary:");
            System.out.println(" Matched rows    : " + matches);
            System.out.println(" Mismatched rows : " + mismatches);
            System.out.println(" Overall Result  : " + (mismatches == 0 ? "PASS" : "FAIL"));
            System.out.println("========================================");

        } finally {
            driver.quit();
        }
    }
}
