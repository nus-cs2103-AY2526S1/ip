@ECHO OFF

REM create bin directory if it doesn't exist
if not exist ..\bin mkdir ..\bin

REM delete output from previous run
if exist ACTUAL.TXT del ACTUAL.TXT

REM compile the code into the bin folder
del /q sources.txt 2>nul
for /R ..\src\main\java %%f in (*.java) do echo %%f>>sources.txt
javac -encoding UTF-8 -d ..\bin @sources.txt
IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    exit /b 1
)
REM no error here, errorlevel == 0

REM run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath ..\bin Lenny < input.txt > ACTUAL.TXT

REM Compare ACTUAL.TXT with EXPECTED.TXT
fc /n ACTUAL.TXT EXPECTED.TXT > diff.txt

if errorlevel 1 (
    echo Test result: FAILED
    type diff.txt
    exit /b 1
) else (
    echo Test result: PASSED
)

