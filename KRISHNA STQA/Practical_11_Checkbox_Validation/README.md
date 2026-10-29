# KRISHNA STQA - Practical 11: Checkbox Identification and Validation

## Aim
Web page par total checkboxes count karna, checked/unchecked ginna, dynamic selection karna aur har change ke baad counts validate karna.

## Requirements
- JDK 17, Eclipse, Selenium Java 4.49.0
- Google Chrome
- Python (local server)

## Folder Contents
- `webpage/checkbox_page.html` : 6 checkboxes (Java & JavaScript pre-checked)
- `webpage/start_local_server.bat` : Server start (port 8000)
- `selenium-checkbox-practical11/` : Maven project com.stqa:selenium-checkbox-practical11 with `CheckboxValidationTest`
- `Screenshots/` : apni run ke screenshots yahan save karo (journal ke liye)

## Steps to Run
1. `webpage/start_local_server.bat` chalao.
2. `selenium-checkbox-practical11` ko Eclipse me import karo, JRE = JDK 17.
3. `CheckboxValidationTest.java` -> Run As -> Java Application.
4. Expected: Total 6, Initially checked 2 / unchecked 4, Check-all 6/6, Uncheck-all 0/6, selective 2/6, sab PASS.

## Result
The program identified all checkboxes, counted checked and unchecked states, performed three dynamic selection operations and validated that the state changes matched expectations.
