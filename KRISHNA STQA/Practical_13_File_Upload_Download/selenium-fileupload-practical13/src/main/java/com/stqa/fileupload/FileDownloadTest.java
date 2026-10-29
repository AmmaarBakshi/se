package com.stqa.fileupload;

import java.io.File;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class FileDownloadTest {

    // Downloads go to the project's "downloads" folder
    static final String DOWNLOAD_DIR = new File("downloads").getAbsolutePath();

    static final String WEB_URL = "https://the-internet.herokuapp.com/download";

    public static void main(String[] args) throws Exception {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        // Configure Chrome to auto-download to a specific folder
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("download.default_directory", DOWNLOAD_DIR);
        prefs.put("download.prompt_for_download", false);
        prefs.put("download.directory_upgrade", true);
        prefs.put("safebrowsing.enabled", true);
        options.setExperimentalOption("prefs", prefs);

        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        try {
            driver.get(WEB_URL);
            System.out.println("Opened: " + WEB_URL);
            System.out.println();

            // Click on the download link for sample.txt
            System.out.println("Clicking on download link 'sample.txt'...");
            driver.findElement(By.linkText("sample.txt")).click();

            // Wait a few seconds for the download to complete
            System.out.println("Waiting for download to complete...");
            Thread.sleep(4000);

            // Verify the file exists in the download folder
            File downloadedFile = new File(DOWNLOAD_DIR, "sample.txt");
            System.out.println();
            System.out.println("=== DOWNLOAD VERIFICATION ===");
            System.out.println("Expected file path : " + downloadedFile.getAbsolutePath());
            System.out.println("File exists        : " + downloadedFile.exists());
            System.out.println("File size (bytes)  : " + downloadedFile.length());
            System.out.println("Download Result    : " +
                    (downloadedFile.exists() ? "PASS" : "FAIL"));

            // Clean up before next run
            if (downloadedFile.exists()) {
                downloadedFile.delete();
                System.out.println("Cleanup            : File deleted for next run");
            }

        } catch (Exception e) {
            System.out.println("Test error: " + e.getMessage());
        } finally {
            driver.quit();
            System.out.println("\nBrowser closed. Test complete.");
        }
    }
}
