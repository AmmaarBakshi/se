# KRISHNA STQA - Practical 16: TestNG Framework Implementation and Reporting

## Aim
TestNG annotations se automated test cases banana, groups (smoke/regression) aur priorities define karna aur HTML test report generate karna.

## Requirements
- JDK 17, Eclipse, Selenium Java 4.49.0
- TestNG 7.10.2 + Eclipse plugin
- Google Chrome

## Folder Contents
- `selenium-testng-practical16/src/test/java/com/stqa/tests/SmokeAndRegressionTests.java` : 5 tests: 2 smoke + 3 regression with priorities
- `selenium-testng-practical16/testng.xml` : Suite `STQA TestNG Suite` (Smoke Tests + Regression Tests)
- `selenium-testng-practical16/` : Maven project (POM classes + BaseTest, Practical 15 se reuse)
- `Screenshots/` : apni run ke screenshots yahan save karo (journal ke liye)

## Steps to Run
1. `selenium-testng-practical16` ko Eclipse me import karo, JRE = JDK 17.
2. `testng.xml` par right-click -> Run As -> TestNG Suite.
3. Project refresh (F5) -> `test-output/index.html` kholo.
4. Expected: Total 5 tests, Passes 5, Failures 0, Skips 0; Smoke aur Regression sections alag; screenshot lo.

## Result
TestNG annotations (@BeforeClass, @BeforeMethod, @Test, @AfterMethod, @AfterClass) structured the lifecycle; tests were grouped into smoke and regression with priorities; TestNG generated HTML reports. Both the full suite and the smoke-only subset executed successfully.
