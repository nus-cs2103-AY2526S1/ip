# AI.md
## Week 2 – Level‑0
Used ChatGPT to draft V-themed greeting/exit lines. Manually integrated and tested.
Observation: Faster copy tweaks; kept logic simple to meet minimal spec.

## Week 2 – Level‑1
Enhanced V with echo functionality using clean code practices:
- Refactored code into logical methods (showLogo, greet, echo, farewell)
- Added Javadoc comments for all methods
- Implemented main input loop with 'bye' condition
- Added proper error handling and resource management
- Maintained V's theatrical personality in all output

## Week 2 – Level‑2
Added task management with V's dramatic style:
- Implemented String[] tasks array with MAX_TASKS limit (100)
- Added task counter and management methods
- Implemented 'list' command to show all tasks
- Added dramatic responses for all operations
- Included divider lines for better output formatting
- Handled edge cases (empty list, max tasks reached)

AI Usage: Used AI to help structure the task management system and generate V's dramatic responses. Manually implemented all logic and verified behavior.

## Week 2 – Level‑3
Refactored to use Task class and added mark/unmark functionality:
- Created Task class with description and isDone state
- Implemented mark() and unmark() methods with V's dramatic flair
- Added input validation for task numbers
- Updated list display to show task status ([X] or [ ])
- Maintained V's theatrical voice in all responses
- Improved error handling for invalid inputs

AI Usage: Used AI to help design the Task class and generate V's dramatic responses for the mark/unmark functionality. Manually implemented the core logic and verified behavior.

## Week 2 – Level‑4
Implemented task types (Todo, Deadline, Event) with inheritance:
- Created abstract Task class with common functionality
- Extended Task with Todo, Deadline, and Event subclasses
- Added support for new commands: todo, deadline, event
- Implemented proper parsing for task descriptions and dates/times
- Enhanced list display to show task types and additional details
- Maintained V's theatrical style in all responses
- Added input validation and error handling for all commands

AI Usage: Used AI to help design the class hierarchy and generate V's dramatic responses. Manually implemented the command parsing and task management logic.

## Week 2 – Level‑5
Refactored to use ArrayList for dynamic task management:
- Replaced fixed-size array with `ArrayList<Task>`
- Added input validation and error handling for all commands
- Maintained V's theatrical style in all responses

## Week 2 – Level‑6
Implemented enums for better code organization and type safety:
- Created `CommandType` enum for command parsing
- Added `TaskType` enum for task categorization
- Implemented `TaskStatus` enum for task state management
- Refactored command parsing to use enums
- Improved code maintainability and type safety
- Added comprehensive error handling for invalid commands
- Maintained V's theatrical personality in all responses
- Removed MAX_TASKS limitation
- Updated all task operations to use ArrayList methods
- Added proper error handling for empty lists
- Improved task counting with proper pluralization
- Added more descriptive error messages

AI Usage: Used AI to help design the enum structure and generate V's dramatic responses. Manually implemented the enum logic and integrated it with the existing codebase.
Added delete functionality and improved user experience:
- Implemented 'delete' command to remove tasks
- Added proper task renumbering after deletion
- Enhanced error handling for invalid task numbers
- Updated help text to include delete command
- Improved output formatting for better readability
- Added confirmation messages for task deletion
- Maintained V's dramatic personality in all responses

AI Usage: Used AI to help design the delete functionality and generate V's dramatic responses. Manually implemented the core logic and verified behavior.
