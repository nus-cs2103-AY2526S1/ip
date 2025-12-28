# Git Flow Merge Documentation (GFMD)

## Project: Mr. Meeseeks Task Manager

### Overview
This document provides a comprehensive overview of the Git flow and merge history for the Mr. Meeseeks Task Manager project, including all feature branches, improvements, and the final integrated codebase.

---

## Branch Structure and Merge History

### 1. Core Feature Branches

#### A. `branch-A-Assertions`
**Purpose**: Add Java assertions to document important assumptions throughout the codebase.

**Key Changes**:
- Added assertions to `TaskList` for index bounds checking and null validation
- Added assertions to `Parser` for input validation and command format checking
- Added assertions to `Meeseeks` for constructor and method parameter validation
- Added assertions to `Storage` for file path and TaskList validation
- Added assertions to `AddCommand` for parameter validation

**Merge Commit**: `8b8c5aa` - "Merge branch-A-Assertions: Add assertions to document important assumptions"

#### B. `branch-A-CodeQuality`
**Purpose**: Improve code quality across the entire codebase.

**Key Changes**:
- Encapsulated Task class fields (made private) and added proper getters
- Replaced `Boolean` with `boolean` for consistency
- Added constants to eliminate magic numbers and strings
- Improved Parser class with constants for commands and separators
- Enhanced Storage class with try-with-resources for better resource management
- Added proper null checks and directory creation logic
- Added `isEmpty()` method to TaskList for better API
- Deprecated `listTasks()` method in favor of `getTasksAsString()` for separation of concerns

**Merge Commit**: `a3b572c` - "Resolve merge conflict in Parser.java"

#### C. `branch-A-Streams`
**Purpose**: Implement Java streams features throughout the codebase.

**Key Changes**:
- Refactored `FindCommand` to use streams for filtering tasks by keyword
- Enhanced `TaskList` with stream-based methods:
  - `findTasksByKeyword()` for efficient task searching
  - `getCompletedTaskCount()` and `getPendingTaskCount()` for statistics
  - Improved `getTasksAsString()` using `IntStream` and `Collectors.joining()`
- Updated `Storage` class to use streams for loading and saving tasks
- Added new `StatsCommand` that demonstrates advanced stream usage
- Updated `Parser` to support the new stats command

**Merge Commit**: `7dbb872` - "Resolve merge conflicts and complete streams integration"

#### D. `BCD-Extension`
**Purpose**: Add comprehensive support for managing recurring tasks.

**Key Changes**:
- Created `RecurrenceFrequency` enum with DAILY, WEEKLY, MONTHLY, YEARLY options
- Implemented `RecurringTask` class extending Task with recurrence functionality
- Automatic next due date calculation when task is marked as done
- Overdue detection and days until due calculation
- `AddRecurringCommand` for creating recurring tasks
- `ListOverdueCommand` for viewing overdue recurring tasks
- Updated Parser to handle 'recurring' and 'overdue' commands
- Full file format support for saving/loading recurring tasks
- Comprehensive test coverage for recurring task functionality

**Merge Commit**: `479e7e4` - "Merge BCD-Extension: Add support for managing recurring tasks"

### 2. GUI Improvements
**Purpose**: Modernize the user interface with improved UX and visual design.

**Key Changes**:
- **Asymmetric Conversation Design**: Different styling for user vs app messages
- **Error Highlighting**: Distinct red styling for error messages to catch attention
- **Enhanced Styling**: Modern rounded corners, shadows, and improved typography
- **Space Optimization**: Compact design with reduced profile picture sizes
- **Window Resizing**: Fully resizable window with responsive layout
- **Improved UX**: Keyboard shortcuts, better input validation, enhanced welcome message
- **CSS Styling**: Consistent theming and smooth transitions

**Latest Commit**: `7937157` - "Addition of commands on the add recurring command"

---

## Merge Conflicts and Resolution

### Conflict 1: Parser.java (branch-A-CodeQuality merge)
**Issue**: Constants from code quality branch conflicted with assertions from assertions branch.

**Resolution**: Combined both approaches:
- Kept all assertion statements for input validation
- Used constants for command strings and separators
- Maintained both code quality improvements and assertion documentation

### Conflict 2: Storage.java (branch-A-Streams merge)
**Issue**: Improved directory handling conflicted with stream-based content generation.

**Resolution**: Combined both approaches:
- Used improved directory creation logic from code quality branch
- Applied stream-based content generation from streams branch
- Maintained both error handling improvements and functional programming patterns

---

## Final Integrated Features

### Core Functionality
1. **Task Management**: Todo, Deadline, Event, and Recurring tasks
2. **Command Processing**: Comprehensive command parsing with error handling
3. **Data Persistence**: File-based storage with robust error handling
4. **Search and Statistics**: Find tasks and view completion statistics

### Advanced Features
1. **Recurring Tasks**: Full support for daily, weekly, monthly, yearly recurring tasks
2. **Overdue Management**: Automatic detection and listing of overdue tasks
3. **Stream Processing**: Modern Java streams for efficient data manipulation
4. **Assertion Documentation**: Comprehensive input validation and assumption documentation

### User Interface
1. **Modern Chat Interface**: Asymmetric design with distinct message types
2. **Error Highlighting**: Prominent error display for invalid commands
3. **Responsive Design**: Resizable window with proper content scaling
4. **Enhanced UX**: Keyboard shortcuts, input validation, and professional styling

---

## Quality Assurance

### Testing
- All existing tests pass
- New comprehensive test suite for recurring tasks
- Full integration testing across all features
- GUI functionality testing

### Code Quality
- Proper encapsulation and data hiding
- Consistent coding standards
- Comprehensive error handling
- Modern Java features (streams, assertions)
- Clean, maintainable code structure

### Documentation
- Inline code documentation
- Comprehensive commit messages
- Clear feature descriptions
- User-friendly command examples

---

## Deployment and Usage

### Commands Available
- `todo <task>` - Add a todo
- `deadline <task> /by <date>` - Add a deadline
- `event <task> /from <start> /to <end>` - Add an event
- `recurring <task> /every <frequency> /due <date>` - Add recurring task
- `list` - Show all tasks
- `find <keyword>` - Search tasks
- `overdue` - Show overdue recurring tasks
- `stats` - Show task statistics
- `bye` - Exit

### Example Usage
```
recurring Weekly team meeting /every weekly /due 15/1/2024 1400
recurring Daily standup /every daily /due 16/1/2024 0900
overdue
stats
```

---

## Repository Status

- **Main Branch**: `master` (fully integrated)
- **Remote Repository**: `https://github.com/xiaotong0329/ip.git`
- **All Feature Branches**: Successfully merged and pushed
- **Tags Created**: 
  - `BCD-Extension` - Recurring tasks feature
  - `A-Assertions` - Assertions feature
  - `A-CodeQuality` - Code quality improvements
  - `A-Streams` - Streams implementation

---

## Conclusion

The Mr. Meeseeks Task Manager project has successfully evolved from a basic task management system to a comprehensive, modern application with:

- **Robust Core Functionality**: Complete task management with multiple task types
- **Advanced Features**: Recurring tasks, overdue management, and statistics
- **Modern Implementation**: Java streams, assertions, and clean architecture
- **Professional UI**: Modern chat interface with excellent user experience
- **High Code Quality**: Proper encapsulation, error handling, and testing

All features have been successfully integrated, tested, and deployed to the main branch, providing a complete and professional task management solution.
