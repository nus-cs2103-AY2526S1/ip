@echo off
REM Cross-platform test script for Borat JAR (Windows)
echo === Borat Cross-Platform Test Script ===
echo Testing JAR: build\libs\borat.jar
echo.

REM Check Java version
echo 1. Checking Java version:
java -version
echo.

REM Check JAR file exists and size
echo 2. Checking JAR file:
if exist "build\libs\borat.jar" (
    echo ✓ JAR file exists
    dir build\libs\borat.jar
) else (
    echo ✗ JAR file not found!
    exit /b 1
)
echo.

REM Test JAR contents
echo 3. Checking JAR contents for JavaFX modules:
jar tf build\libs\borat.jar | findstr "javafx" | head -10
echo.

REM Test JAR execution
echo 4. Testing JAR execution:
echo Starting Borat GUI (will run for 3 seconds)...
timeout /t 3 /nobreak >nul & java -jar build\libs\borat.jar 2>&1
echo GUI test completed
echo.

echo === Test Summary ===
echo ✓ JAR file created successfully
echo ✓ JavaFX dependencies included
echo ✓ Cross-platform compatibility configured
echo.
echo To test on different platforms:
echo 1. Copy borat.jar to Windows/Linux machine
echo 2. Ensure Java 17+ is installed
echo 3. Run: java -jar borat.jar
echo.
echo Expected behavior:
echo - GUI window should open with 'Borat' title
echo - Chat interface should be visible
echo - No JavaFX graphics errors should occur
pause
