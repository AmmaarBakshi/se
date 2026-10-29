# KRISHNA STQA - Practical 18: Cross-Browser Testing Using Selenium Grid

## Aim
Selenium Grid se Chrome, Firefox aur Edge par automation script chalana aur execution time tatha compatibility issues compare karna.

## Requirements
- Windows 10/11
- JDK 17, Eclipse, Selenium Java 4.49.0
- Google Chrome, Mozilla Firefox, Microsoft Edge
- selenium-server-4.49.0.jar (is folder me rakho)

## Folder Contents
- `start_hub.bat` : Hub (port 4444)
- `start_node_chrome.bat / start_node_firefox.bat / start_node_edge.bat` : Nodes on ports 5555 / 5556 / 5557
- `selenium-crossbrowser-grid/` : Maven project com.stqa:selenium-crossbrowser-grid with `CrossBrowserGridTest`
- `Screenshots/` : apni run ke screenshots yahan save karo (journal ke liye)

## Steps to Run
1. `start_hub.bat` chalao (window band mat karo).
2. Teen alag command prompts me teeno `start_node_*.bat` chalao.
3. http://localhost:4444/ui/ kholo -> teen nodes dikhne chahiye; screenshot lo.
4. `selenium-crossbrowser-grid` ko Eclipse me import karo, JRE = JDK 17.
5. `CrossBrowserGridTest.java` -> Run As -> Java Application; har browser ka Time (ms) note karke comparison table banao.

## Result
The Selenium Grid was configured with a Hub and three Nodes (Chrome, Firefox, Edge). A single test was executed across all three browsers using RemoteWebDriver; execution times were compared and no cross-browser compatibility issues were found for the tested page.
