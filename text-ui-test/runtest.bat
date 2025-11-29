@echo off
setlocal ENABLEDELAYEDEXPANSION

REM Make paths work regardless of where its run from
set SCRIPT_DIR=%~dp0
pushd "%SCRIPT_DIR%"

REM Ensure bin exists
if not exist "..\bin" mkdir "..\bin"

REM Clean previous ACTUAL
if exist "ACTUAL.TXT" del "ACTUAL.TXT"

REM Build a sources list recursively (Windows-safe, no wildcard issues)
del /q sources.txt 2>nul
for /R "..\src\main\java" %%f in (*.java) do (
  echo %%f>>sources.txt
)

REM Compile everything listed in sources.txt into ..\bin
javac -Xlint:none -d "..\bin" @sources.txt
IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    popd
    exit /b 1
)

REM Run app (main class is larry.Larry), redirect input -> ACTUAL.TXT
java -classpath "..\bin" larry.Larry < "input.txt" > "ACTUAL.TXT"

REM Compare actual vs expected (assignment typically uses EXPECTED.TXT)
FC "EXPECTED.TXT" "ACTUAL.TXT" >NUL
IF ERRORLEVEL 1 (
    echo Test result: FAIL
    popd
    exit /b 1
) ELSE (
    echo Test result: PASS
)

popd
