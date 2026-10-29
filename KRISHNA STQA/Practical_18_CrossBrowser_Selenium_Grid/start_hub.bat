@echo off
REM Put selenium-server-4.49.0.jar in THIS folder. Do not close this window.
set JAVA17="C:\Program Files\Java\jdk-17\bin\java.exe"
cd /d "%~dp0"
%JAVA17% -jar selenium-server-4.49.0.jar hub
