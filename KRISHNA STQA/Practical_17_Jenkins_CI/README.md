# KRISHNA STQA - Practical 17: Continuous Integration using Jenkins

## Aim
Jenkins ko configure karna taaki Selenium automation scripts automatically execute hon, build reports generate hon aur test execution history analyze ho sake.

## Requirements
- Windows 10/11
- JDK 17 + JAVA_HOME set
- Eclipse IDE
- Jenkins (latest LTS) + plugins (Maven Integration, HTML Publisher)
- Google Chrome
- Maven project with Selenium tests (POM practical se)

## Folder Contents
- `selenium-jenkins-practical17/` : Maven project with Surefire plugin + `testng.xml` (Jenkins ke liye ready)
- `jenkins_job_settings.txt` : Jenkins job ki saari settings step-by-step
- `Screenshots/` : apni run ke screenshots yahan save karo (journal ke liye)

## Steps to Run
1. Pehle command prompt me `selenium-jenkins-practical17` folder me `mvn clean test` chalao (pass hona chahiye).
2. Jenkins install karo, initial setup + plugins install karo.
3. `jenkins_job_settings.txt` ke hisaab se Global Tools aur Freestyle job `STQA_Selenium_Build` banao.
4. Build Now -> Console Output; TestNG Report link se report dekho.
5. Build History (blue/yellow/red) aur Trend se execution history analyze karo; screenshots lo.

## Result
Jenkins was configured to execute the Selenium automation scripts automatically. The TestNG HTML report was published inside Jenkins and the build history gave a visual record of test execution over time.
