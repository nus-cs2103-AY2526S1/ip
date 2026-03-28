@ECHO OFF
SETLOCAL

chcp 65001 >NUL

set SRC=..\src\main\java
set BIN=..\bin
set MAIN=Hachi

IF NOT EXIST "%BIN%" MKDIR "%BIN%"
DEL /Q ACTUAL.TXT 2>NUL

DIR /S /B "%SRC%\*.java" > sources.txt
javac -encoding UTF-8 -Xlint:none -d "%BIN%" @sources.txt
IF ERRORLEVEL 1 (
    ECHO ********** BUILD FAILURE **********
    EXIT /B 1
)

java -Dfile.encoding=UTF-8 -cp "%BIN%" %MAIN% < input.txt > ACTUAL.TXT
IF ERRORLEVEL 1 (
    ECHO ********** RUNTIME FAILURE **********
    EXIT /B 1
)

REM compare the output to the expected output
FC ACTUAL.TXT EXPECTED.TXT
FC ACTUAL.TXT EXPECTED.TXT >NUL
IF ERRORLEVEL 1 (
    ECHO ********** TEST FAILURE **********
    EXIT /B 1
) ELSE (
    ECHO ********** TEST SUCCESS **********
)


ENDLOCAL

