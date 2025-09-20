@echo off
echo ===================================
echo CS2103T Buddy JAR Test for Windows
echo ===================================
echo.

REM Check if Java is installed
java -version >nul 2>&1
if %errorlevel% neq 0 (
    echo ERROR: Java is not installed or not in PATH
    echo Please install Java 17 or later from https://adoptium.net/
    pause
    exit /b 1
)

REM Check if buddy.jar exists
if not exist buddy.jar (
    echo ERROR: buddy.jar not found in current directory!
    echo Please put buddy.jar in the same folder as this script
    pause
    exit /b 1
)

echo Testing buddy.jar in headless mode...
echo ===================================
echo.

REM Create test input file
(
echo list
echo todo Test task on Windows
echo deadline Submit homework /by tomorrow
echo list
echo mark 1
echo list
echo bye
) > test_input.txt

REM Run the test
echo Running test...
type test_input.txt | java -Djava.awt.headless=true -jar buddy.jar

REM Check result
if %errorlevel% equ 0 (
    echo.
    echo ===================================
    echo TEST PASSED! The JAR works correctly.
    echo ===================================
) else (
    echo.
    echo ===================================
    echo TEST FAILED! The JAR crashed.
    echo Error code: %errorlevel%
    echo ===================================
)

REM Clean up
del test_input.txt

echo.
pause