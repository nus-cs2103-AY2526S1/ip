#!/usr/bin/env bash

# Set project root relative to this script
PROJECT_ROOT="$(cd "$(dirname "$0")/.." && pwd)"
SRC_DIR="$PROJECT_ROOT/src/main/java"
BIN_DIR="$PROJECT_ROOT/bin"
INPUT_FILE="$PROJECT_ROOT/text-ui-test/input.txt"
EXPECTED_FILE="$PROJECT_ROOT/text-ui-test/EXPECTED.TXT"
ACTUAL_FILE="$PROJECT_ROOT/text-ui-test/ACTUAL.TXT"

# Create bin directory if it doesn't exist
mkdir -p "$BIN_DIR"

# Delete previous output
rm -f "$ACTUAL_FILE"

# Compile all Java files into bin folder (recursively)
# shellcheck disable=SC2046
if ! javac -Xlint:none -d "$BIN_DIR" $(find "$SRC_DIR" -name "*.java"); then
    echo "********** BUILD FAILURE **********"
    exit 1
fi

# Run the program
java -cp "$BIN_DIR" steven.Steven < "$INPUT_FILE" > "$ACTUAL_FILE"

# Convert expected file to UNIX format and compare
cp "$EXPECTED_FILE" "$EXPECTED_FILE"-UNIX.TXT
dos2unix "$ACTUAL_FILE" "$EXPECTED_FILE"-UNIX.TXT

if diff "$ACTUAL_FILE" "$EXPECTED_FILE"-UNIX.TXT; then
    echo "Test result: PASSED"
    exit 0
else
    echo "Test result: FAILED"
    exit 1
fi
