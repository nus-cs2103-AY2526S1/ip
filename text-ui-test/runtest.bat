@ECHO OFF

REM create bin directory if it doesn't exist
if not exist ..\bin mkdir ..\bin

REM clear leo.txt before testing to avoid errors
echo.> ..\.data\Leo.txt

REM Solution adapted from https://www.perplexity.ai/search/change-branch-name-in-local-an-Fkk8ZHiwSyOZ4IElmJrh_g#11
REM create list of all .java files recursively
dir /s /b ..\src\main\java\*.java > sources.txt

REM delete output from previous run
if exist ACTUAL.TXT del ACTUAL.TXT

REM compile the code into the bin folder
javac  -cp ..\src\main\java -Xlint:none -d ..\bin @sources.txt
IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    exit /b 1
)
REM no error here, errorlevel == 0

REM clear sources.txt
echo.> sources.txt

REM run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath ..\bin leo.Leo < input.txt > ACTUAL.TXT

REM compare the output to the expected output
FC ACTUAL.TXT EXPECTED.TXT
