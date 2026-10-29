@echo off
cd /d "%~dp0"
echo Serving this folder at http://localhost:8000
python -m http.server 8000
