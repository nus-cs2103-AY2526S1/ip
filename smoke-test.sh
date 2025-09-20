#!/bin/bash

# CS2103T iP Smoke Test Script
# This simulates the automated grading environment

echo "==================================="
echo "CS2103T iP Smoke Test"
echo "==================================="
echo ""

# Test configuration
JAR_FILE="build/libs/buddy.jar"
TESTS_PASSED=0
TESTS_FAILED=0

# Check if JAR exists
if [ ! -f "$JAR_FILE" ]; then
    echo "❌ ERROR: $JAR_FILE not found!"
    echo "Run './gradlew shadowJar' first"
    exit 1
fi

echo "Testing JAR: $JAR_FILE"
echo "Timestamp: $(ls -lh $JAR_FILE | awk '{print $6, $7, $8}')"
echo ""

# Function to run test
run_test() {
    local test_name=$1
    local test_cmd=$2

    echo "Running: $test_name"
    echo "----------------------------------------"

    # Run test with timeout
    if timeout 5s bash -c "$test_cmd" > /dev/null 2>&1; then
        echo "✅ PASSED"
        ((TESTS_PASSED++))
    else
        echo "❌ FAILED (Exit code: $?)"
        ((TESTS_FAILED++))
    fi
    echo ""
}

# Test 1: Basic launch in headless mode
run_test "Test 1: Headless mode launch" \
    "echo 'bye' | java -Djava.awt.headless=true -jar $JAR_FILE"

# Test 2: Simple command execution
run_test "Test 2: List command" \
    "echo -e 'list\nbye' | java -Djava.awt.headless=true -jar $JAR_FILE"

# Test 3: Task creation
run_test "Test 3: Create todo" \
    "echo -e 'todo Test task\nbye' | java -Djava.awt.headless=true -jar $JAR_FILE"

# Test 4: Multiple commands
run_test "Test 4: Multiple operations" \
    "echo -e 'todo Task 1\ndeadline Task 2 /by tomorrow\nlist\nmark 1\nbye' | java -Djava.awt.headless=true -jar $JAR_FILE"

# Test 5: EOF handling
run_test "Test 5: EOF handling" \
    "echo 'list' | java -Djava.awt.headless=true -jar $JAR_FILE"

# Test 6: No display environment
run_test "Test 6: No DISPLAY variable" \
    "unset DISPLAY && echo 'bye' | java -jar $JAR_FILE"

echo "==================================="
echo "TEST SUMMARY"
echo "==================================="
echo "✅ Passed: $TESTS_PASSED"
echo "❌ Failed: $TESTS_FAILED"
echo ""

if [ $TESTS_FAILED -eq 0 ]; then
    echo "🎉 ALL TESTS PASSED!"
    echo "Your JAR should pass the automated grading."
    exit 0
else
    echo "⚠️  WARNING: Some tests failed!"
    echo "Fix these issues before submission."
    exit 1
fi