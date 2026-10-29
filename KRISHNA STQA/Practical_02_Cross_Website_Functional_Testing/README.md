# KRISHNA STQA - Practical 2: Cross-Website Functional Testing

## Aim
Do alag websites (SauceDemo, The Internet) ke liye Selenium IDE test suite banana aur links click, form fill, dropdown select, displayed content verify karna.

## Requirements
- Mozilla Firefox + Selenium IDE
- Internet connection

## Folder Contents
- `KRISHNA_STQA_Practical2.side` : Ready Selenium IDE project with 4 test cases + suite `STQA_Practical2_Suite`
- `Screenshots/` : apni run ke screenshots yahan save karo (journal ke liye)

## Steps to Run
1. Firefox me Selenium IDE open karo -> Open an existing project -> `KRISHNA_STQA_Practical2.side`.
2. SauceDemo React app hai, isliye normal `type`/`sendKeys` fail hota hai; isliye username/password `execute script` + input event dispatch se bhare gaye hain.
3. Suite run karo, execution log dekho aur export karo; screenshots Screenshots/ me rakho.

## Result
| Test | Website | Check |
|---|---|---|
| TC01_SauceDemo_Login | saucedemo.com | login -> `css=.title` = Products |
| TC02_SauceDemo_Cart | saucedemo.com | login, add Backpack to cart, open cart -> item name = Sauce Labs Backpack |
| TC03_TheInternet_Dropdown | the-internet.herokuapp.com/dropdown | select Option 2, h3 = Dropdown List |
| TC04_TheInternet_DynamicControl | the-internet.herokuapp.com/dynamic_controls | Remove -> "It's gone!", Add -> "It's back!" |

The test suite was successfully created and executed on two different websites (SauceDemo and The Internet). Functional operations such as login, adding to cart, selecting a dropdown option, and handling dynamic elements were tested. Execution logs were generated and analyzed.
