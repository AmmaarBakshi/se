package com.stqa.util;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotUtil {

    private static final String SCREENSHOT_DIR = "screenshots";

    public static String captureScreenshot(WebDriver driver, String name) {
        try {
            // Make sure folder exists
            File dir = new File(SCREENSHOT_DIR);
            if (!dir.exists()) dir.mkdirs();

            // Take the screenshot
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            // Save to a unique filename
            String fileName = name + "_" + System.currentTimeMillis() + ".png";
            File dest = new File(SCREENSHOT_DIR + File.separator + fileName);
            FileUtils.copyFile(src, dest);

            return dest.getAbsolutePath();
        } catch (IOException e) {
            System.err.println("Failed to capture screenshot: " + e.getMessage());
            return null;
        }
    }
}
