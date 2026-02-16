@ECHO OFF
setlocal enabledelayedexpansion

REM create bin directory if it doesn't exist
if not exist ..\bin mkdir ..\bin

REM delete output from previous run
if exist ACTUAL.TXT del ACTUAL.TXT

REM compile the code into the bin folder
javac  -cp ..\src\main\java -Xlint:none -d ..\bin ..\src\main\java\*.java
IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    exit /b 1
)
REM no error here, errorlevel == 0

REM run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
for %%f in (tests\*_input.txt) do (
    @REM assign current file to variable inputFile
    set "inputFile=%%f"

    @REM build corresponding expected output file path
    set "expectedFile=%%f"
    set "expectedFile=!expectedFile:_input=_expected!"

    @REM build output file
    set "actualFile=%%f"
    set "actualFile=!actualFile:_input=_actual!"

    @REM print current testing file for debugging
    echo Running test %%f...
    java -classpath ..\bin chatbot.ui.chatbot < "!inputFile!" > "!actualFile!"
    REM compare the output to the expected output
    FC "!actualFile!" "!expectedFile!"
    @REM new line so its easier to read
    echo.
)


