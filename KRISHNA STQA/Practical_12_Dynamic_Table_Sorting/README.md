# KRISHNA STQA - Practical 12: Dynamic Web Table Handling and Sorting Validation

## Aim
Dynamic web table se data extract karna, column sorting (text + numeric) validate karna aur filter ke results verify karna.

## Requirements
- JDK 17, Eclipse, Selenium Java 4.49.0
- Google Chrome
- Python (local server)

## Folder Contents
- `webpage/sortable_table.html` : Employee table with click-to-sort headers and name filter
- `webpage/start_local_server.bat` : Server start (port 8000)
- `selenium-dyntable-practical12/` : Maven project com.stqa:selenium-dyntable-practical12 with `DynamicTableSortingTest`
- `Screenshots/` : apni run ke screenshots yahan save karo (journal ke liye)

## Steps to Run
1. `webpage/start_local_server.bat` chalao.
2. `selenium-dyntable-practical12` ko Eclipse me import karo, JRE = JDK 17.
3. `DynamicTableSortingTest.java` -> Run As -> Java Application.
4. Summary me 5 checks PASS dikhne chahiye; screenshot lo.

## Result
The program extracted data from the dynamic table, validated sorting on text and numeric columns and verified filter results. All validations passed.
