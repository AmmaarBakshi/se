# KRISHNA STQA - Practical 5: Login Automation and Validation Using Selenium WebDriver

## Aim
SauceDemo ka login Selenium WebDriver (Java) se automate karna aur URL, page title aur logout-link ki assertions validate karna.

## Requirements
- JDK 17
- Eclipse IDE
- Selenium Java 4.49.0 (Maven)
- Google Chrome
- Credentials: standard_user / secret_sauce

## Folder Contents
- `selenium-login-automation/` : Maven project (com.stqa:selenium-login-automation) with `SauceDemoLoginTest`
- `Screenshots/` : apni run ke screenshots yahan save karo (journal ke liye)

## Steps to Run
1. Eclipse: File -> Import -> Existing Maven Projects -> `selenium-login-automation`.
2. Project Properties -> Java Build Path -> JRE System Library = jdk-17.
3. `SauceDemoLoginTest.java` -> Run As -> Java Application.
4. Console me 3 ASSERTION lines + BONUS PASS dikhni chahiye; screenshot lo.

## Result
Selenium WebDriver (sendKeys works natively here, unlike Selenium IDE) logged in to SauceDemo; URL, page title and logout-link assertions all PASSED.
