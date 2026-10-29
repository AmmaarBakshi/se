# KRISHNA STQA - Practical 15: Page Object Model (POM) Framework Implementation

## Aim
Selenium WebDriver + TestNG ke saath Page Object Model design pattern me test framework banana aur reusable page classes se modular test cases chalana.

## Requirements
- Windows 10/11
- JDK 17, Eclipse, Selenium Java 4.49.0
- TestNG 7.10.2 + TestNG Eclipse plugin
- Google Chrome

## Folder Contents
- `selenium-pom-practical/src/main/java/com/stqa/pages/` : `LoginPage`, `InventoryPage` (locators + actions)
- `selenium-pom-practical/src/test/java/com/stqa/base/BaseTest.java` : Browser setup/teardown (@BeforeMethod / @AfterMethod)
- `selenium-pom-practical/src/test/java/com/stqa/tests/LoginTests.java` : 3 tests: valid login, invalid login, empty fields
- `selenium-pom-practical/testng.xml` : Suite file
- `Screenshots/` : apni run ke screenshots yahan save karo (journal ke liye)

## Steps to Run
1. `selenium-pom-practical` ko Eclipse me import karo (Maven project), JRE = JDK 17.
2. TestNG plugin install karo: Help -> Eclipse Marketplace -> `TestNG`.
3. `LoginTests.java` ya `testng.xml` par right-click -> Run As -> TestNG Test.
4. Console me 3 tests pass; `test-output/index.html` (project refresh ke baad) ka screenshot lo.
5. Note: PDF me `BaseTest` src/main/java me tha, lekin TestNG `test` scope me hai, isliye Maven compile error aata. Isliye `BaseTest` ko `src/test/java` me rakha hai (package same: com.stqa.base).

## Result
The Page Object Model framework was implemented with Selenium WebDriver and TestNG. Page classes encapsulate locators and actions; TestNG manages the lifecycle and generates the HTML report. All three modular test cases passed.
