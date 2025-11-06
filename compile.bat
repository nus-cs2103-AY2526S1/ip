@echo off
setlocal enabledelayedexpansion

set SRC_DIR=src\main\java
set BIN_DIR=bin

if not exist "%BIN_DIR%" mkdir "%BIN_DIR%"

javac -d %BIN_DIR% -cp %SRC_DIR% %SRC_DIR%\enums\*.java
javac -d %BIN_DIR% -cp %BIN_DIR%;%SRC_DIR% %SRC_DIR%\task\*.java
javac -d %BIN_DIR% -cp %BIN_DIR%;%SRC_DIR% %SRC_DIR%\command\*.java
javac -d %BIN_DIR% -cp %BIN_DIR%;%SRC_DIR% %SRC_DIR%\ui\*.java
javac -d %BIN_DIR% -cp %BIN_DIR%;%SRC_DIR% %SRC_DIR%\storage\*.java
javac -d %BIN_DIR% -cp %BIN_DIR%;%SRC_DIR% %SRC_DIR%\parser\*.java
javac -d %BIN_DIR% -cp %BIN_DIR%;%SRC_DIR% %SRC_DIR%\V.java

echo Compilation complete.
