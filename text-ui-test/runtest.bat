@ECHO OFF

REM create bin directory if it doesn't exist
if not exist ..\bin mkdir ..\bin

REM Solution below to pass in argument to .bat file adapted from: https://stackoverflow.com/questions/26551/how-can-i-pass-arguments-to-a-batch-file
set fileName=%1

REM delete output from previous run
if exist ACTUAL_%fileName%.TXT del ACTUAL_%fileName%.TXT

REM compile the code into the bin folder
javac  -cp ..\src\main\java -Xlint:none -d ..\bin ..\src\main\java\zell\*.java
IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    exit /b 1
)
REM no error here, errorlevel == 0

REM run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath ..\bin Zell < input_%fileName%.txt > ACTUAL_%fileName%.TXT

REM compare the output to the expected output
FC ACTUAL_%fileName%.TXT EXPECTED_%fileName%.TXT

rmdir /s /q data