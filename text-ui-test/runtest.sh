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

# clear data file to start fresh
if [ -e "../data/lyra.txt" ]
then
    rm ../data/lyra.txt
fi

# also clear data file in current directory (in case program runs from here)
if [ -e "./data/lyra.txt" ]
then
    rm ./data/lyra.txt
fi

# compile all Java sources into the bin folder, terminate if error occurs
find ../src/main/java -name "*.java" > sources.txt
if ! javac -cp ../src/main/java -Xlint:none -d ../bin @sources.txt
then
    echo "********** BUILD FAILURE **********"
    exit 1
fi
rm -f sources.txt

# run the program (package main class), feed commands and capture output
java -classpath ../bin lyra.Lyra < input.txt > ACTUAL.TXT

# convert to UNIX format
dos2unix ACTUAL.TXT

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