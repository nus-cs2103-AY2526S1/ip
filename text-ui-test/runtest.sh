#!/usr/bin/env bash

# create bin directory if it doesn't exist
if [ ! -d "../bin" ]
then
    mkdir ../bin
fi

# delete output from previous run
if [ -e "./ACTUAL.TXT" ]
then
    rm ACTUAL.TXT
fi

# ensure test save file is clean
if [ -e "./test-bestbot.txt" ]
then
    rm ./test-bestbot.txt
fi
touch ./test-bestbot.txt

# compile the code into the bin folder, terminates if error occurred
if ! javac -cp ../src/main/java -Xlint:none -d ../bin ../src/main/java/bestbot/*.java
then
    echo "********** BUILD FAILURE **********"
    exit 1
fi

# run the program with a TEST save file path, feed commands from input.txt and redirect output
java -classpath ../bin bestbot.Bestbot ./test-bestbot.txt < input.txt > ACTUAL.TXT

# convert to UNIX format
cp EXPECTED.TXT EXPECTED-UNIX.TXT
dos2unix ACTUAL.TXT EXPECTED-UNIX.TXT

# compare the output to the expected output
diff ACTUAL.TXT EXPECTED-UNIX.TXT
if [ $? -eq 0 ]
then
    echo "Test result: PASSED"
    exit 0
else
    echo "Test result: FAILED"
    exit 1
fi
