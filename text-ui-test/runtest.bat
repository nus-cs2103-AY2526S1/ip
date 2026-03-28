@ECHO OFF

REM create bin directory if it doesn't exist
if not exist ..\bin mkdir ..\bin

REM delete output from previous run
if exist ACTUAL.TXT del ACTUAL.TXT

REM compile each Java file individually
javac -cp "C:\Users\User\Desktop\CS2103T individual project\ip\src\main\java" -Xlint:none -d ..\bin "C:\Users\User\Desktop\CS2103T individual project\ip\src\main\java\DeadLine.java"
javac -cp "C:\Users\User\Desktop\CS2103T individual project\ip\src\main\java" -Xlint:none -d ..\bin "C:\Users\User\Desktop\CS2103T individual project\ip\src\main\java\Dukey.java"
javac -cp "C:\Users\User\Desktop\CS2103T individual project\ip\src\main\java" -Xlint:none -d ..\bin "C:\Users\User\Desktop\CS2103T individual project\ip\src\main\java\DukeyException.java"
javac -cp "C:\Users\User\Desktop\CS2103T individual project\ip\src\main\java" -Xlint:none -d ..\bin "C:\Users\User\Desktop\CS2103T individual project\ip\src\main\java\Event.java"
javac -cp "C:\Users\User\Desktop\CS2103T individual project\ip\src\main\java" -Xlint:none -d ..\bin "C:\Users\User\Desktop\CS2103T individual project\ip\src\main\java\Task.java"
javac -cp "C:\Users\User\Desktop\CS2103T individual project\ip\src\main\java" -Xlint:none -d ..\bin "C:\Users\User\Desktop\CS2103T individual project\ip\src\main\java\ToDo.java"

REM check if javac was successful
IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    exit /b 1
)

REM run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath ..\bin Dukey < input.txt > ACTUAL.TXT

REM compare the output to the expected output
FC ACTUAL.TXT EXPECTED.TXT
IF %ERRORLEVEL% EQU 0 (
    echo Test result: PASSED
    exit /b 0
) ELSE (
    echo Test result: FAILED
    exit /b 1
)


