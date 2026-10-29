# KRISHNA STQA - Practical 13: File Upload and Download Automation

## Aim
File upload (sendKeys aur Robot class se) aur download automate karna, upload ka message validate karna aur downloaded file ki existence verify karna.

## Requirements
- Windows 10/11
- JDK 17, Eclipse, Selenium Java 4.49.0
- Google Chrome
- Python (local server)
- Internet (download test ke liye)

## Folder Contents
- `webpage/upload_page.html` : File upload demo page
- `webpage/start_local_server.bat` : Server start (port 8000)
- `selenium-fileupload-practical13/data/sample.txt` : Upload karne wali file
- `selenium-fileupload-practical13/downloads/` : Download folder
- `selenium-fileupload-practical13/` : Maven project com.stqa:selenium-fileupload-practical13 with `FileUploadTest` and `FileDownloadTest`
- `Screenshots/` : apni run ke screenshots yahan save karo (journal ke liye)

## Steps to Run
1. `webpage/start_local_server.bat` chalao.
2. `selenium-fileupload-practical13` ko Eclipse me import karo, JRE = JDK 17.
3. `FileUploadTest.java` -> Run As -> Java Application (Robot step ke time mouse/keyboard ko mat chhedo).
4. `FileDownloadTest.java` -> Run As -> Java Application.
5. Dono ke console output ke screenshots lo. File paths code me project-relative hain, isliye alag se path edit nahi karna.

## Result
The script automated file upload using sendKeys() and the Robot class, validated the upload through the page's confirmation message, and verified that a downloaded file exists in the target directory.
