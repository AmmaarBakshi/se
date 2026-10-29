package com.stqa.webelement;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.*;

public class WebElementInteractionTest {

    public static void main(String[] args) throws InterruptedException {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();

        // Implicit wait - applies globally to every findElement
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        // Explicit wait - used for a specific condition
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        try {
            // 1. TEXTBOX
            driver.get("https://the-internet.herokuapp.com/inputs");
            WebElement txt = driver.findElement(By.tagName("input"));
            txt.sendKeys("12345");
            System.out.println("1. Textbox : " + (txt.getAttribute("value").equals("12345") ? "PASS" : "FAIL"));

            // 2. RADIO BUTTON
            driver.get("https://demoqa.com/radio-button");
            Thread.sleep(1500);
            driver.findElement(By.xpath("//label[text()='Yes']")).click();
            System.out.println("2. Radio : " + (driver.findElement(By.id("yesRadio")).isSelected() ? "PASS" : "FAIL"));

            // 3. CHECKBOX (toggle two boxes)
            driver.get("https://the-internet.herokuapp.com/checkboxes");
            List<WebElement> cbs = driver.findElements(By.cssSelector("input[type='checkbox']"));
            if (!cbs.get(0).isSelected()) cbs.get(0).click();
            if (cbs.get(1).isSelected()) cbs.get(1).click();
            System.out.println("3. Checkbox : " + (cbs.get(0).isSelected() && !cbs.get(1).isSelected() ? "PASS" : "FAIL"));

            // 4. DROPDOWN (Select class)
            driver.get("https://the-internet.herokuapp.com/dropdown");
            Select select = new Select(driver.findElement(By.id("dropdown")));
            select.selectByVisibleText("Option 1");
            select.selectByIndex(2);
            System.out.println("4. Dropdown : " + (select.getFirstSelectedOption().getText().equals("Option 2") ? "PASS" : "FAIL"));

            // 5. ALERT (accept OK, dismiss Cancel)
            driver.get("https://the-internet.herokuapp.com/javascript_alerts");
            driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
            Thread.sleep(500);
            driver.switchTo().alert().accept();
            Thread.sleep(500);
            String r1 = driver.findElement(By.id("result")).getText();
            driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
            Thread.sleep(500);
            driver.switchTo().alert().dismiss();
            Thread.sleep(500);
            String r2 = driver.findElement(By.id("result")).getText();
            System.out.println("5. Alert : " + (r1.contains("successfully clicked") && r2.contains("Cancel") ? "PASS" : "FAIL"));

            // 6. IFRAME (switch in and back out)
            driver.get("https://the-internet.herokuapp.com/iframe");
            driver.switchTo().frame("mce_0_ifr");
            String inner = driver.findElement(By.id("tinymce")).getText();
            driver.switchTo().defaultContent();
            String outer = driver.findElement(By.tagName("h3")).getText();
            System.out.println("6. Iframe : " + (inner.contains("Your content") && outer.contains("An iFrame") ? "PASS" : "FAIL"));

            // 7. EXPLICIT WAIT (element appears after ~5 sec)
            driver.get("https://the-internet.herokuapp.com/dynamic_loading/2");
            driver.findElement(By.xpath("//button[text()='Start']")).click();
            WebElement hello = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//h4[text()='Hello World!']")));
            System.out.println("7. ExpWait : " + (hello.getText().equals("Hello World!") ? "PASS" : "FAIL"));

            System.out.println("\nAll element interactions completed.");

        } catch (Exception e) {
            System.out.println("Test error: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }
}
