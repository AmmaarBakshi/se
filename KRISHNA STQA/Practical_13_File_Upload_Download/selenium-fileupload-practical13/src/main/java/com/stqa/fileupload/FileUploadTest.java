package com.stqa.fileupload;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class FileUploadTest {

    // sample.txt is inside the project's data folder (absolute path is required by sendKeys)
    static final String FILE_PATH = new File("data/sample.txt").getAbsolutePath();

    static final String WEB_URL = "http://localhost:8000/upload_page.html";

    public static void main(String[] args) throws Exception {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        try {
            driver.get(WEB_URL);
            System.out.println("Opened: " + WEB_URL);
            System.out.println();

            // ===== METHOD 1: Direct sendKeys (works for <input type='file'>) =====
            System.out.println("=== METHOD 1: sendKeys on standard file input ===");
            WebElement fileInput = driver.findElement(By.id("fileUpload"));
            fileInput.sendKeys(FILE_PATH);
            Thread.sleep(1000);
            System.out.println("File path sent via sendKeys.");
            System.out.println();

            // Click the Upload button
            driver.findElement(By.id("uploadBtn")).click();
            Thread.sleep(1500);

            // Read the message shown by the page
            String message = driver.findElement(By.id("message")).getText();
            System.out.println("Page message : " + message);
            System.out.println("Upload Result: " +
                    (message.contains("sample.txt") ? "PASS" : "FAIL"));
            System.out.println();

            // ===== METHOD 2: Robot Class (fallback for custom upload buttons) =====
            // This is demonstrated separately because our page has a standard input.
            // The Robot method is used when the file dialog opens at the OS level.
            System.out.println("=== METHOD 2: Robot Class (simulated file dialog) ===");
            System.out.println("Copying file path to clipboard...");
            StringSelection selection = new StringSelection(FILE_PATH);
            Toolkit.getDefaultToolkit().getSystemClipboard()
                    .setContents(selection, null);
            System.out.println("Press Ctrl+V then Enter via Robot...");

            Robot robot = new Robot();
            robot.delay(500);
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.delay(500);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
            robot.delay(500);

            System.out.println("Robot sequence executed.");
            System.out.println("(The Robot method is a fallback for cases where");
            System.out.println(" the file input is a custom element that opens");
            System.out.println(" a Windows file dialog.)");
            System.out.println();

            System.out.println("=========================================");
            System.out.println("FILE UPLOAD VERIFICATION SUMMARY");
            System.out.println("=========================================");
            System.out.println("Upload method used : sendKeys");
            System.out.println("File selected      : sample.txt");
            System.out.println("Validation         : " + (message.contains("sample.txt") ? "PASS" : "FAIL"));
            System.out.println("=========================================");

        } catch (Exception e) {
            System.out.println("Test error: " + e.getMessage());
        } finally {
            driver.quit();
            System.out.println("\nBrowser closed. Test complete.");
        }
    }
}
