@echo off
cd /d "%~dp0"
echo Serving http://localhost:8000/student_table.html
python -m http.server 8000
