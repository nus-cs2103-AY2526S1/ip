#!/usr/bin/env bash

# create bin directory if it doesn't exist
if [ ! -d "../bin" ]
then
    mkdir ../bin
fi

INPUT_DIR="./inputs"
OUTPUT_DIR="./outputs"
RESULT_DIR="./results"

# create results directory if it doesn't exist
if [ ! -d "$RESULT_DIR" ]
then
    mkdir "$RESULT_DIR"
fi

# remove all past contents of results directory
if [ -d "$RESULT_DIR" ]; then
  if [ -n "$(ls -A "$RESULT_DIR")" ]; then
    rm -rf "$RESULT_DIR"/* "$RESULT_DIR"/.[!.]* "$RESULT_DIR"/..?*
  fi
fi

# compile the code into the bin folder, terminates if error occurred
if ! javac -cp ../src/main/java -Xlint:none -d ../bin ../src/main/java/minhgpt/*.java
then
    echo "********** BUILD FAILURE **********"
    exit 1
fi


for infile in "$INPUT_DIR"/*; do
    fname=$(basename "$infile")
    outfile="$OUTPUT_DIR/$fname"
    resultfile="$RESULT_DIR/$fname"

    # Run your program (replace ./program with your command)
    java -classpath ../bin MinhGPT --fresh < "$infile" > "$resultfile"

    if diff -q "$resultfile" "$outfile" > /dev/null; then
        echo "$fname: PASS"
    else
        echo "$fname: FAIL"
    fi
done
