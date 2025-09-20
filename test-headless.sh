#!/bin/bash

# Test script for headless environments
echo "Testing Buddy in headless mode..."

# Create test input
cat > test_input.txt << EOF
list
todo Buy groceries
deadline Submit assignment /by tomorrow
event Team meeting /from 2pm /to 4pm
list
mark 1
list
delete 1
list
bye
EOF

# Run test
echo "Running JAR with test commands..."
java -Djava.awt.headless=true -jar build/libs/buddy.jar < test_input.txt

# Check exit code
if [ $? -eq 0 ]; then
    echo "✅ Test passed: JAR runs successfully in headless mode"
else
    echo "❌ Test failed: JAR crashed in headless mode"
    exit 1
fi

# Clean up
rm test_input.txt