# KRISHNA STQA - Practical 1: Creation of Test Suite Using Selenium IDE

## Aim
Selenium IDE install karke minimum 4 test cases ka test suite banana (HTML page, XML response, JSON API output, dynamic form). Suite run karke WebDriver format me export karna.

## Requirements
- Mozilla Firefox (recommended) + Selenium IDE extension
- VS Code / Notepad
- Python (local web server ke liye)
- Internet connection

## Folder Contents
- `index.html` : HTML page (TC01)
- `xml_response.html` : XML wrapped in HTML (TC02)
- `json_response.html` : JSON wrapped in HTML (TC03)
- `dynamic.html` : Dynamic form (TC04)
- `KRISHNA_STQA_Practical1.side` : Ready Selenium IDE project: 4 test cases + suite `STQA_Practical1_Suite`
- `start_local_server.bat` : python -m http.server 8000 start karta hai
- `Screenshots/` : apni run ke screenshots yahan save karo (journal ke liye)

## Steps to Run
1. Is folder me `start_local_server.bat` double-click karo (ya `python -m http.server 8000`). Window band mat karo.
2. Firefox me Selenium IDE extension install karo aur open karo.
3. Open an existing project -> `KRISHNA_STQA_Practical1.side` select karo (ya manually 4 test cases banao, neeche table dekho).
4. Test suite `STQA_Practical1_Suite` select karke 'Run all tests' dabao.
5. Suite ke 3-dot menu se Export -> Java JUnit (WebDriver format) karke script save karo; screenshots Screenshots/ me rakho.

## Result
Test cases:

| Test | Commands |
|---|---|
| TC01_HTML_Page | open index.html; assert text `css=h1` = Software Testing and Quality Assurance |
| TC02_XML_Response | open xml_response.html; assert text `css=#studentName` = Vicky |
| TC03_JSON_API | open json_response.html; assert text `css=#userName` = Leanne Graham |
| TC04_Dynamic_Form | open dynamic.html; execute script (name = Vicky); select `id=course` = label=Computer Science; click `id=agree`; click `id=submit`; assert text `css=#message` = Form submitted successfully - Vicky - Computer Science |

All four test cases (HTML, XML, JSON, Dynamic Form) were created and executed successfully in Selenium IDE. The test suite was exported to WebDriver format.
