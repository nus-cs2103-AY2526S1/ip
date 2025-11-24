#!/usr/bin/env bash

# Set paths
SRC_DIR="../src/main/java"
BIN_DIR="../bin"
INPUT_FILE="text-ui-test/input.txt"
ACTUAL_FILE="text-ui-test/ACTUAL.TXT"
EXPECTED_FILE="text-ui-test/EXPECTED.TXT"
EXPECTED_UNIX="text-ui-test/EXPECTED-UNIX.TXT"
MAIN_CLASS="BurgerBurglar"

# create bin directory if it doesn't exist
mkdir -p "$BIN_DIR"

# delete previous output
rm -f "$ACTUAL_FILE"

# compile Java files
if ! javac -Xlint:none -d "$BIN_DIR" src/main/java/*.java; then
    echo "********** BUILD FAILURE **********"
    exit 1
fi

# run program with input redirection
java -cp "$BIN_DIR" "$MAIN_CLASS" < "$INPUT_FILE" > "$ACTUAL_FILE"

# convert to UNIX format (use sed if dos2unix is missing)
if command -v dos2unix >/dev/null 2>&1; then
    cp "$EXPECTED_FILE" "$EXPECTED_UNIX"
    dos2unix "$ACTUAL_FILE" "$EXPECTED_UNIX"
else
    # fallback: remove \r manually
    cp "$EXPECTED_FILE" "$EXPECTED_UNIX"
    sed -i '' 's/\r$//' "$ACTUAL_FILE"
    sed -i '' 's/\r$//' "$EXPECTED_UNIX"
fi

# compare output
if diff "$ACTUAL_FILE" "$EXPECTED_UNIX" >/dev/null; then
    echo "Test result: PASSED"
    exit 0
else
    echo "Test result: FAILED"
    diff "$ACTUAL_FILE" "$EXPECTED_UNIX"
    exit 1
fi
