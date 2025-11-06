@echo off
setlocal enabledelayedexpansion

set SRC_DIR=src\main\java
set BIN_DIR=bin

if not exist "%BIN_DIR%" mkdir "%BIN_DIR%"

echo Compiling enums...
if not exist "%BIN_DIR%\enums" mkdir "%BIN_DIR%\enums"
javac -d %BIN_DIR% %SRC_DIR%\enums\*.java

echo Compiling task...
if not exist "%BIN_DIR%\task" mkdir "%BIN_DIR%\task"
javac -d %BIN_DIR% -cp %BIN_DIR% %SRC_DIR%\task\*.java

echo Compiling command...
if not exist "%BIN_DIR%\command" mkdir "%BIN_DIR%\command"
javac -d %BIN_DIR% -cp %BIN_DIR% %SRC_DIR%\command\*.java

echo Compiling ui...
if not exist "%BIN_DIR%\ui" mkdir "%BIN_DIR%\ui"
javac -d %BIN_DIR% -cp %BIN_DIR% %SRC_DIR%\ui\*.java

echo Compiling storage...
if not exist "%BIN_DIR%\storage" mkdir "%BIN_DIR%\storage"
javac -d %BIN_DIR% -cp %BIN_DIR% %SRC_DIR%\storage\*.java

echo Compiling parser...
if not exist "%BIN_DIR%\parser" mkdir "%BIN_DIR%\parser"
javac -d %BIN_DIR% -cp %BIN_DIR% %SRC_DIR%\parser\*.java

echo Compiling main class...
javac -d %BIN_DIR% -cp %BIN_DIR% %SRC_DIR%\V.java

echo Running application...
java -cp %BIN_DIR% V
