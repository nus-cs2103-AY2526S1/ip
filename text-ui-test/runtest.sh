#!/usr/bin/env bash

# create bin directory if it doesn't exist
if [ ! -d "../bin" ]
then
    mkdir ../bin
fi

# remove old compiled classes
rm -rf ../bin/*

# delete output from previous run
if [ -e "./ACTUAL.TXT" ]
then
    rm ./ACTUAL.TXT
fi

if [ -e "./data/tronalddump.txt" ]
then
    rm ./data/tronalddump.txt
fi


# compile the code into the bin folder, terminates if error occurred
SRC=../src/main/java
BIN=../bin
find "$SRC" -name "*.java" > sources.txt
if ! javac -Xlint:none -d "$BIN" @sources.txt
then
    echo "********** BUILD FAILURE **********"
    exit 1
fi

# run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath ../bin core.TronaldDump < input2.txt > ACTUAL.TXT

# convert to UNIX format
cp EXPECTED2.TXT EXPECTED2-UNIX.TXT
dos2unix ACTUAL.TXT EXPECTED2-UNIX.TXT

# compare the output to the expected output
diff ACTUAL.TXT EXPECTED2-UNIX.TXT
if [ $? -eq 0 ]
then
    echo "Test result: PASSED"
    exit 0
else
    echo "Test result: FAILED"
    exit 1
fi