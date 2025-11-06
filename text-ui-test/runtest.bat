@ECHO OFF
chcp 65001 > nul

REM Set script directory to the project root
cd /d "%~dp0.."

REM Set project paths
SET BIN_DIR=bin
SET SRC_DIR=src\main\java

REM Create bin directory if it doesn't exist
if not exist "%BIN_DIR%" mkdir "%BIN_DIR%"

REM Delete previous ACTUAL.TXT if it exists
if exist "text-ui-test\ACTUAL.TXT" del "text-ui-test\ACTUAL.TXT"

REM Ensure test starts with a clean data file
if exist "v_data.txt" del "v_data.txt"

echo Compiling...
cd /d "%SRC_DIR%"
javac -Xlint:none -d "..\..\..\bin" -cp "." *.java command\*.java parser\*.java storage\*.java task\*.java ui\*.java
if errorlevel 1 (
    echo ********** BUILD FAILURE **********
    exit /b 1
)

echo Running tests...
cd /d "%~dp0.."
java -classpath "%BIN_DIR%" V < "text-ui-test\input.txt" > "text-ui-test\ACTUAL.TXT" 2> "text-ui-test\error.log"
echo Test run finished.

echo Comparing with expected output...
fc /A /L "text-ui-test\ACTUAL.TXT" "text-ui-test\EXPECTED.TXT"
if %ERRORLEVEL% EQU 0 (
    echo Test result: PASSED
    exit /b 0
) else (
    echo Test result: FAILED
    exit /b 1
)
