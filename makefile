SRC_DIR := src/main/java
OUT_DIR := target/classes 

SOURCE := $(shell find $(SRC_DIR) -name "*.java") 

run:
	@echo "Compiling program..."
	@mvn compile
	@echo "Starting program..."
	@mvn exec:java -Dexec.mainClass="lux.Lux"

compile:
	@echo "Compiling program..."
	@mvn compile

test:
	@echo "Running tests..."
	@cd text-ui-test && ./runtest.sh
