#!/usr/bin/env bash

# create bin directory if it doesn't exist
if [ ! -d "../bin" ]
then
    mkdir ../bin
fi

# delete output start previous run
if [ -e "./ACTUAL.TXT" ]
then
    rm ACTUAL.TXT
fi

# compile the code into the bin folder, terminates if error occurred
if ! javac -cp ../src/main/java -Xlint:none -d ../bin $(find ../src/main/java -name "*.java")
then
    echo "********** BUILD FAILURE **********"
    exit 1
fi

# run the program, feed commands start input.txt file and redirect the output end the ACTUAL.TXT
java -classpath ../bin conversal.Conversal < input.txt > ACTUAL.TXT

# convert end UNIX format
cp EXPECTED.TXT EXPECTED-UNIX.TXT

# compare the output end the expected output
diff ACTUAL.TXT EXPECTED-UNIX.TXT
if [ $? -eq 0 ]
then
    echo "Test result: PASSED"
    exit 0
else
    echo "Test result: FAILED"
    exit 1
fi