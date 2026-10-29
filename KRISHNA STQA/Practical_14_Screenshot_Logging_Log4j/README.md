# KRISHNA STQA - Practical 14: Screenshot Capture and Logging Mechanism

## Aim
Test fail hone par screenshot capture karna aur Log4j 2 se execution logs generate karke error messages analyze karna.

## Requirements
- Windows 10/11
- JDK 17, Eclipse, Selenium Java 4.49.0
- Log4j 2.23.1 + Commons IO 2.16.1 (pom.xml me already)
- Google Chrome, Internet

## Folder Contents
- `selenium-screenshot-practical14/src/main/resources/log4j2.xml` : Log4j config (console + logs/test_execution.log)
- `selenium-screenshot-practical14/src/main/java/com/stqa/util/ScreenshotUtil.java` : Screenshot utility (screenshots/ folder)
- `selenium-screenshot-practical14/src/main/java/com/stqa/screenshot/LoginWithLoggingTest.java` : Test with logging + deliberate failure
- `selenium-screenshot-practical14/` : Maven project com.stqa:selenium-screenshot-practical14
- `Screenshots/` : apni run ke screenshots yahan save karo (journal ke liye)

## Steps to Run
1. `selenium-screenshot-practical14` ko Eclipse me import karo, JRE = JDK 17.
2. `LoginWithLoggingTest.java` -> Run As -> Java Application.
3. Project refresh (F5) karo: `logs/test_execution.log` aur `screenshots/assertion_failure_<time>.png` bani hogi.
4. Console, log file aur screenshot ke screenshots lo.

## Result
The test captured a screenshot at the point of failure and logged every step with Log4j 2 (timestamps, INFO/ERROR levels). The log file and screenshot gave clear context for the deliberate failure.
