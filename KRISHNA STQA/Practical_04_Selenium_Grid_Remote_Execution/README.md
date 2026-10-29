# KRISHNA STQA - Practical 4: Selenium Server (Grid) Configuration and Remote Execution

## Aim
Selenium Server ko Hub-Node architecture me configure karna, RemoteWebDriver se multiple browsers par test script chalana aur cross-browser results compare karna.

## Requirements
- JDK 17 (C:\Program Files\Java\jdk-17)
- Eclipse IDE (2025-06 ya later)
- Selenium Server 4.49.0 jar (selenium-server-4.49.0.jar) - download karke is folder me rakho
- Selenium Java Client 4.49.0 (Maven)
- Google Chrome + Mozilla Firefox
- Internet

## Folder Contents
- `start_hub.bat` : Hub start karta hai (port 4444)
- `start_node.bat` : Node start karta hai (port 5555)
- `selenium-grid-practical4/` : Maven project (com.stqa:selenium-grid-practical4) with `GridCrossBrowserTest`
- `Screenshots/` : apni run ke screenshots yahan save karo (journal ke liye)

## Steps to Run
1. `start_hub.bat` chalao; ~20 sec baad `Started Selenium Hub 4.49.0 ... http://<ip>:4444` dikhega.
2. Browser me http://localhost:4444/ui/ kholo.
3. Dusre command prompt me `start_node.bat` chalao (`Started Selenium node ... :5555`).
4. Grid UI refresh karo: node (Chrome, Firefox, Edge), Sessions: 0, Max Concurrency: 12 dikhna chahiye - screenshot lo.
5. Eclipse me `selenium-grid-practical4` ko Existing Maven Project ki tarah import karo, JRE JDK 17 set karo.
6. `GridCrossBrowserTest.java` -> Run As -> Java Application. Console output ka screenshot lo.

## Result
Comparison table (journal ke liye):

| Test Parameter | Chrome | Firefox |
|---|---|---|
| Browser session created | Pass | Pass |
| Page opened | Pass | Pass |
| Title verification | Example Domain | Example Domain |
| URL verification | https://example.com/ | https://example.com/ |
| Overall result | Pass | Pass |

Selenium Grid (Hub + Node) was configured and the test executed on Chrome and Firefox through RemoteWebDriver; results were compared.
