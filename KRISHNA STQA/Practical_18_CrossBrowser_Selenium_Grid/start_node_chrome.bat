@echo off
REM Run in its own command prompt after the hub is up.
set JAVA17="C:\Program Files\Java\jdk-17\bin\java.exe"
cd /d "%~dp0"
%JAVA17% -jar selenium-server-4.49.0.jar node --port 5555 --selenium-manager true
