#!/usr/bin/env bash

# Delete bin directory if it exists, then recreate it
if [ -d "../bin" ]; then
    rm -rf ../bin
fi
mkdir ../bin

# Copy testTasks.txt to tasks.txt
cp ./data/testTasks.txt ./data/tasks.txt

# Delete output from previous run
if [ -e "./ACTUAL.TXT" ]; then
    rm ACTUAL.TXT
fi

# Compile the code into the bin folder, terminate if compilation fails
if ! javac -cp ../src/main/java -Xlint:none -d ../bin ../src/main/java/stewie/*.java; then
    echo "********** BUILD FAILURE **********"
    exit 1
fi

# Run the program, feed commands from input.txt and redirect output to ACTUAL.TXT
java -classpath ../bin stewie.Stewie < input.txt > ACTUAL.TXT

# Convert expected and actual output to UNIX format
cp EXPECTED.TXT EXPECTED-UNIX.TXT
dos2unix ACTUAL.TXT EXPECTED-UNIX.TXT 2> /dev/null

# Remove lines 7 to 52 before diffing
tail -n +6 ACTUAL.TXT | sed '1,46d' > ACTUAL.TXT
tail -n +6 EXPECTED-UNIX.TXT | sed '1,46d' > EXPECTED-UNIX.TXT

# Compare actual and expected output
diff ACTUAL.TXT EXPECTED-UNIX.TXT
if [ $? -eq 0 ]; then
    echo "Test result: PASSED"
    exit 0
else
    echo "Test result: FAILED"
    exit 1
fi
