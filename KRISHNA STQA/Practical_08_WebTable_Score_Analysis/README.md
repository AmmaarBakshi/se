# KRISHNA STQA - Practical 8: Data Extraction and Score Analysis from Web Table

## Aim
Selenium WebDriver se web table se student marks extract karna aur kitne students ne kam se kam ek subject me 60+ score kiya uska count aur percentage nikalna.

## Requirements
- JDK 17, Eclipse, Selenium Java 4.49.0
- Python (local server)
- Google Chrome

## Folder Contents
- `webpage/student_table.html` : Student table page
- `webpage/start_local_server.bat` : Server start (port 8000)
- `selenium-webtable-practical8/` : Maven project com.stqa:selenium-webtable-practical8 with `WebTableScoreAnalysis`
- `Screenshots/` : apni run ke screenshots yahan save karo (journal ke liye)

## Steps to Run
1. `webpage/start_local_server.bat` chalao.
2. `selenium-webtable-practical8` ko Eclipse me import karo, JRE = JDK 17.
3. `WebTableScoreAnalysis.java` -> Run As -> Java Application.
4. Console summary ka screenshot lo.

## Result
The Selenium WebDriver program successfully extracted student marks from the HTML table, applied the score criterion, and computed the total count and percentage. Out of 10 students, 8 (80.0%) scored 60 or above in at least one subject.
