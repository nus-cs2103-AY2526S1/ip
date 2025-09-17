# AI Usage Log

I've used the IDE (vscode) built-in Copilot, specifically Claude Sonnet 4, to help improve the code quality of the app.

## Code Quality Improvements - September 17, 2025

**User Request:** "Can you help to improve the quality of the current codebase? Please also add a summary of what you've done and the prompt to the `AI.md` file."

### Summary of Improvements Made

#### 1. Enhanced Exception Handling
- **Parser.java**: Added proper `NumberFormatException` handling in `parseInts()` method to prevent crashes from invalid numeric input
- **Storage.java**: Improved error handling with proper exception propagation instead of silencing errors
- **Bob.java**: Enhanced constructor error handling to gracefully handle storage initialization failures

#### 2. Resource Management Improvements
- **Storage.java**: Replaced manual resource management with try-with-resources pattern for both `FileWriter` and `Scanner`
- Improved file I/O operations to ensure proper resource cleanup

#### 3. Encapsulation and Data Hiding
- **Task.java**: Changed `isDone` field from `protected` to `private` and added proper getter method
- **TaskList.java**: Updated to use the new `isDone()` getter method instead of direct field access

#### 4. Code Organization and Constants
- **Ui.java**: Extracted magic strings and numbers into proper constants:
  - `LINE_SEPARATOR` for the line separator pattern
  - `ERROR_PREFIX` for consistent error message formatting  
  - `ANSI_RED` and `ANSI_RESET` for console color codes
- **Todo.java**: Added constants for task type symbol and command keyword

#### 5. Documentation Improvements  
- Enhanced JavaDoc comments across multiple classes with proper parameter descriptions and return value documentation
- Added missing `@param` and `@return` annotations where needed
- Improved method descriptions for better code maintainability

#### 6. Error Message Consistency
- **Storage.java**: Replaced direct `System.out.println()` calls with proper exception throwing for consistent error handling
- **TaskList.java**: Added null safety check for storage operations

#### 7. Input Validation Enhancements
- **Parser.java**: Improved parsing logic with better error messages and empty string filtering
- **Storage.java**: Enhanced file parsing with proper empty line handling

### Technical Details

**Files Modified:**
- `src/main/java/bob/parser/Parser.java` - Exception handling improvements
- `src/main/java/bob/task/Task.java` - Encapsulation improvements  
- `src/main/java/bob/task/TaskList.java` - Method calls and null safety
- `src/main/java/bob/storage/Storage.java` - Resource management and error handling
- `src/main/java/bob/Bob.java` - Constructor error handling
- `src/main/java/bob/ui/Ui.java` - Constants and documentation
- `src/main/java/bob/task/Todo.java` - Constants and documentation
- `AI.md` - This documentation

**Key Benefits:**
- More robust error handling prevents crashes from invalid input
- Better resource management prevents memory leaks
- Improved encapsulation follows Java best practices
- Enhanced maintainability through better documentation
- Consistent error reporting throughout the application

## Task Implementation Consistency Improvements - September 17, 2025

**User Request:** "Can you make the changes you had done in Todo to other implementation of the `Task` abstract class? Please also add a summary of what you've done and the prompt to the `AI.md` file."

### Summary of Additional Improvements Made

#### 1. Consistent Constants Implementation
Applied the same constant extraction pattern from `Todo.java` to both `Deadline.java` and `Event.java`:

- **Deadline.java**: Added constants:
  - `TASK_TYPE_SYMBOL = "[D]"` for consistent display formatting
  - `COMMAND_KEYWORD = "deadline"` for parsing operations
  - `TIME_SEPARATOR = "/by"` for command syntax

- **Event.java**: Added constants:
  - `TASK_TYPE_SYMBOL = "[E]"` for consistent display formatting  
  - `COMMAND_KEYWORD = "event"` for parsing operations
  - `START_SEPARATOR = "/from"` for start time parsing
  - `END_SEPARATOR = "/to"` for end time parsing

#### 2. Enhanced Documentation Consistency
- **Deadline.java**: Improved JavaDoc comments with proper `@param` and `@return` annotations
- **Event.java**: Enhanced constructor and method documentation for consistency with `Todo.java`
- Standardized method descriptions across all Task implementations

#### 3. Code Maintainability Improvements
- Eliminated magic strings by using constants in all string operations
- Made parsing logic more maintainable by centralizing command keywords
- Improved consistency in toString() and toInputString() methods

### Technical Details

**Files Modified in This Session:**
- `src/main/java/bob/task/Deadline.java` - Constants and documentation improvements
- `src/main/java/bob/task/Event.java` - Constants and documentation improvements  
- `AI.md` - Updated documentation

**Consistency Achieved:**
- All Task subclasses (`Todo`, `Deadline`, `Event`) now follow the same pattern for constants
- Unified JavaDoc documentation style across all implementations
- Consistent use of constants instead of magic strings
- Improved code readability and maintainability

**Key Benefits:**
- **Maintainability**: Changes to command keywords or symbols only need to be made in one place
- **Consistency**: All task types follow the same coding patterns and documentation style
- **Readability**: Constants make the code self-documenting and easier to understand
- **Extensibility**: Future task types can follow the same established pattern

## Command Classes Quality Improvements - September 17, 2025

**User Request:** "Are there any improvements to the commands?"

### Summary of Command Improvements Made

#### 1. Constants for Magic Strings and Messages
Applied consistent constant extraction across all command classes to eliminate magic strings:

- **MarkCommand**: Added constants for single/multiple task prompts and error messages
- **UnmarkCommand**: Added constants following the same pattern as MarkCommand
- **DeleteCommand**: Added constants for task removal messages and count display
- **AddCommand**: Added constant for success message format
- **FindCommand**: Added constant for search result message
- **ExitCommand**: Added constant for exit message
- **InvalidCommand**: Added constant for error message format

#### 2. Enhanced Error Handling Consistency
- **Standardized Error Reporting**: Changed task validation commands to use `ui.showError()` instead of `ui.printSection()` for better error categorization
- **Consistent Error Messages**: All commands now use the same format for "Invalid task number(s)" errors
- **Better Exception Handling**: AddCommand now uses `ui.showError()` for input validation errors

#### 3. Improved Documentation
- **Complete JavaDoc**: Added comprehensive `@param` annotations for all execute() methods
- **Method Descriptions**: Enhanced descriptions to clarify parameter usage and command behavior
- **Parameter Documentation**: Clearly indicated which parameters are unused in specific commands

#### 4. Code Organization and Maintainability
- **Centralized Message Constants**: All user-facing messages are now defined as constants
- **Consistent Formatting**: All commands follow the same pattern for constants and error handling
- **Reduced Duplication**: Eliminated repeated string literals across command classes

### Technical Details

**Files Modified in This Session:**
- `src/main/java/bob/command/MarkCommand.java` - Constants and error handling improvements
- `src/main/java/bob/command/UnmarkCommand.java` - Constants and error handling improvements
- `src/main/java/bob/command/DeleteCommand.java` - Constants and error handling improvements
- `src/main/java/bob/command/AddCommand.java` - Constants and error handling improvements
- `src/main/java/bob/command/FindCommand.java` - Constants and documentation improvements
- `src/main/java/bob/command/ExitCommand.java` - Constants and documentation improvements
- `src/main/java/bob/command/ListCommand.java` - Documentation improvements
- `src/main/java/bob/command/InvalidCommand.java` - Constants and documentation improvements
- `AI.md` - Updated documentation

**Consistency Achieved:**
- All command classes now follow uniform patterns for constants and error handling
- Unified approach to error reporting using appropriate UI methods
- Comprehensive JavaDoc documentation across all command implementations
- Consistent message formatting and user experience

**Key Benefits:**
- **Maintainability**: Changes to messages require updates in only one location per class
- **User Experience**: Consistent error reporting and message formatting
- **Code Quality**: Eliminates magic strings and improves code readability
- **Documentation**: Complete parameter documentation for better code understanding
- **Error Handling**: Proper distinction between errors and informational messages

## Comprehensive Test Coverage Enhancement - September 17, 2025

**User Request:** "Can you help to add more tests for the app? The junit test case naming should have the following format: `unitBeingTested_descriptionOfTestInputs_expectedOutcome`. Please include the prompt and short summary of the changes in the `AI.md`"

### Summary of Test Improvements Made

#### 1. Enhanced Existing Test Files with Comprehensive Coverage
- **ParserTest.java**: Expanded from 9 basic tests to 25+ comprehensive tests covering:
  - Edge cases for all command types (todo, deadline, event, mark, unmark, delete, find, list, bye)
  - Invalid input handling (missing parameters, invalid numbers, empty strings)
  - Case sensitivity testing (uppercase, mixed case, lowercase commands)
  - Multiple number parsing for mark/unmark/delete operations
  - Whitespace-only input validation

- **TaskTest.java**: Expanded from 7 basic tests to 30+ comprehensive tests covering:
  - All task types (Todo, Deadline, Event) creation and validation
  - Marking/unmarking functionality with edge cases
  - Description search functionality with partial matches and edge cases
  - DateTime parsing and formatting for all formats
  - Input string conversion for storage operations
  - Invalid input handling for all task creation scenarios

#### 2. Created New Comprehensive Test Files
- **TaskListTest.java**: Created with 25+ tests covering:
  - Basic functionality (add, remove, get, size, isEmpty)
  - Task manipulation (markDone, unmarkDone with validation)
  - Index validation and boundary testing
  - String representation and formatting
  - Filtering operations with keyword matching
  - Storage string conversion for persistence
  - Static utility method testing

- **StorageTest.java**: Created with 20+ tests covering:
  - File creation and directory handling
  - Save and load operations with round-trip testing
  - Error handling for invalid file formats and permissions
  - Edge cases (empty files, trailing newlines, whitespace)
  - Task state preservation through save/load cycles
  - Invalid input format handling and recovery

- **UiTest.java**: Created with 25+ tests covering:
  - Message display functionality with line separators
  - Error message formatting with ANSI color codes
  - Section printing with proper formatting
  - Help message display with all command information
  - Response buffer functionality and state management
  - Integration testing for mixed operations
  - Edge cases for null input handling

#### 3. Test Naming Convention Implementation
All tests now follow the specified format: `unitBeingTested_descriptionOfTestInputs_expectedOutcome`

**Examples:**
- `parse_todoCommandWithDescription_returnsAddCommand()`
- `createFromString_deadlineWithInvalidDateFormat_throwsInvalidInputException()`
- `markDone_validIndex_marksTaskAsDone()`
- `loadTaskList_fileWithMultipleTasks_createsAllTasks()`
- `showError_simpleErrorMessage_displaysWithErrorPrefix()`

#### 4. Comprehensive Coverage Areas
- **Positive Testing**: Valid inputs and expected successful outcomes
- **Negative Testing**: Invalid inputs and proper error handling
- **Edge Cases**: Boundary conditions, empty inputs, null values
- **Integration Testing**: Multi-step operations and state persistence
- **Error Recovery**: Graceful handling of malformed data and file issues

### Technical Details

**Test Files Created/Enhanced:**
- `src/test/java/bob/parser/ParserTest.java` - Enhanced with 25+ tests (15 additional)
- `src/test/java/bob/task/TaskTest.java` - Enhanced with 30+ tests (23 additional)  
- `src/test/java/bob/task/TaskListTest.java` - Created with 25+ comprehensive tests
- `src/test/java/bob/storage/StorageTest.java` - Created with 20+ comprehensive tests
- `src/test/java/bob/ui/UiTest.java` - Created with 25+ comprehensive tests

**Test Coverage Statistics:**
- **Total Tests**: 100+ comprehensive test cases (previously ~16)
- **Test Files**: 5 comprehensive test files covering all major components
- **Coverage Areas**: Parser, Task hierarchy, TaskList operations, Storage persistence, UI display
- **Naming Convention**: 100% compliance with `unitBeingTested_descriptionOfTestInputs_expectedOutcome` format

**Key Testing Improvements:**
- **Robustness**: Comprehensive edge case testing ensures application stability
- **Maintainability**: Clear test names make test purpose immediately apparent
- **Documentation**: Tests serve as living documentation of expected behavior
- **Regression Prevention**: Extensive coverage prevents future code changes from breaking existing functionality
- **Quality Assurance**: Multiple test scenarios validate both happy path and error conditions

**Benefits:**
- **Reliability**: Comprehensive testing ensures robust application behavior
- **Maintainability**: Well-named tests make debugging and maintenance easier
- **Documentation**: Tests provide clear examples of expected component behavior
- **Confidence**: Extensive coverage provides confidence in code changes and refactoring
