package friday;

/**
 * Enumeration of all supported commands in the Friday task management system.
 * Each command represents a specific action that can be performed by users.
 * 
 * Commands are parsed from user input strings and used to determine
 * the appropriate action to execute.
 * 
 * @author Friday Development Team
 * @version 2.0
 * @since 1.0
 */
public enum Command {
    /** Exit the application */
    BYE,
    
    /** Create a new ToDo task */
    TODO,
    
    /** Create a new Deadline task with a due date */
    DEADLINE,
    
    /** Create a new Event task with start and end times */
    EVENT,
    
    /** Display all tasks */
    LIST,
    
    /** Delete a specific task by index */
    DELETE,
    
    /** Mark a task as completed */
    MARK,
    
    /** Mark a task as not completed */
    UNMARK,
    
    /** Search for tasks by keyword */
    FIND,
    
    /** Show all completed tasks */
    DONE,
    
    /** Show all pending (incomplete) tasks */
    PENDING,
    
    /** Display help information */
    HELP;

    /**
     * Parses a user input string and returns the corresponding Command enum value.
     * 
     * The parsing is case-insensitive and uses different matching strategies:
     * - Exact match for simple commands (bye, list, done, pending, help)
     * - Prefix match for commands that take parameters (todo, deadline, event, delete, mark, unmark, find)
     * 
     * @param input The user input string to parse
     * @return The corresponding Command enum value
     * @throws IllegalArgumentException if the input doesn't match any known command
     * 
     * @examples
     * - "todo buy milk" returns Command.TODO
     * - "LIST" returns Command.LIST
     * - "deadline submit report /by Friday" returns Command.DEADLINE
     * - "invalid" throws IllegalArgumentException
     */
    public static Command getCommand(String input) {
        if (input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException("Command cannot be null or empty");
        }
        
        String lowerInput = input.toLowerCase().trim();
        
        // Exact match commands
        if (lowerInput.equals("bye")) return Command.BYE;
        if (lowerInput.equals("list")) return Command.LIST;
        if (lowerInput.equals("done")) return Command.DONE;
        if (lowerInput.equals("pending")) return Command.PENDING;
        if (lowerInput.equals("help")) return Command.HELP;
        
        // Prefix match commands (for commands that take parameters)
        if (lowerInput.startsWith("todo")) return Command.TODO;
        if (lowerInput.startsWith("deadline")) return Command.DEADLINE;
        if (lowerInput.startsWith("event")) return Command.EVENT;
        if (lowerInput.startsWith("delete")) return Command.DELETE;
        if (lowerInput.startsWith("mark")) return Command.MARK;
        if (lowerInput.startsWith("unmark")) return Command.UNMARK;
        if (lowerInput.startsWith("find")) return Command.FIND;
        
        throw new IllegalArgumentException("Unknown command: " + input);
    }
}
