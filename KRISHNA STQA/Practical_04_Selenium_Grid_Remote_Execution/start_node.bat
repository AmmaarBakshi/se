@echo off
REM Run this in a SECOND command prompt after the hub has started.
set JAVA17="C:\Program Files\Java\jdk-17\bin\java.exe"
cd /d "%~dp0"
%JAVA17% -jar selenium-server-4.49.0.jar node --selenium-manager true
