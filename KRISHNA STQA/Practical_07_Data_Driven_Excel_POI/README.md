# KRISHNA STQA - Practical 7: Data-Driven Testing Using Excel Integration

## Aim
Selenium WebDriver aur Apache POI se Excel file ke 10 student records update karna (Total, Result) aur web application ke table se unko validate karna.

## Requirements
- JDK 17, Eclipse, Selenium Java 4.49.0
- Apache POI 5.2.5 (poi + poi-ooxml, pom.xml me already)
- Python (HTML serve karne ke liye)
- Google Chrome, Microsoft Excel

## Folder Contents
- `webpage/student_table.html` : Web page with student table (Total + Result)
- `webpage/start_local_server.bat` : Server start (port 8000)
- `selenium-excel-practical7/data/student_records.xlsx` : Input Excel (Roll No, Name, Physics, Chemistry, Maths) - 10 records
- `selenium-excel-practical7/` : Maven project com.stqa:selenium-excel-practical7 with `ExcelWebValidationTest`
- `Screenshots/` : apni run ke screenshots yahan save karo (journal ke liye)

## Steps to Run
1. `webpage/start_local_server.bat` chalao (http://localhost:8000/student_table.html browser me check kar lo).
2. `selenium-excel-practical7` ko Eclipse me import karo, JRE = JDK 17.
3. Excel/HTML paths code me relative hain (`data/student_records.xlsx`), isliye project folder se hi run karo (Eclipse me default).
4. `ExcelWebValidationTest.java` -> Run As -> Java Application.
5. `data/student_records_updated.xlsx` Excel me khol kar Total/Result columns ka screenshot lo.

## Result
Rule: Result = Pass agar kisi bhi ek subject me 60 ya zyada marks, warna Fail. Expected: Pass = 8, Fail = 2 (Anil, Pooja); all 10 rows MATCH; Overall Result: PASS.

Apache POI se 10 student records update hue (Total + Result) aur Selenium WebDriver ne web table ke saath compare karke validate kiya.
