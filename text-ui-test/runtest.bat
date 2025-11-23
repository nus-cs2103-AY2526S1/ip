@ECHO OFF

REM create bin directory if it doesn't exist
if exist ..\bin rmdir /s /q ..\bin
mkdir ..\bin

copy /Y .\data\testTasks.txt .\data\tasks.txt

REM delete output from previous run
if exist ACTUAL.TXT del ACTUAL.TXT

REM compile the code into the bin folder
javac  -cp ..\src\main\java -Xlint:none -d ..\bin ..\src\main\java\stewie\*.java
IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    exit /b 1
)
REM no error here, errorlevel == 0

REM run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath ..\bin stewie.Stewie < input.txt > ACTUAL.TXT

REM remove logo: lines 7 to 52 from both files before comparing
setlocal enabledelayedexpansion
set line=0
(
    for /f "usebackq delims=" %%A in ("ACTUAL.TXT") do (
        set /a line+=1
        if !line! lss 6 (
            echo %%A
        ) else if !line! gtr 52 (
            echo %%A
        )
    )
) > ACTUAL-FILTERED.TXT

set line=0
(
    for /f "usebackq delims=" %%A in ("EXPECTED.TXT") do (
        set /a line+=1
        if !line! lss 6 (
            echo %%A
        ) else if !line! gtr 52 (
            echo %%A
        )
    )
) > EXPECTED-FILTERED.TXT

REM compare filtered files
FC ACTUAL-FILTERED.TXT EXPECTED-FILTERED.TXT
endlocal