package pero.command;

/**
 * Command class represents a parsed user command in the Pero task management application.
 */
public class Command {
    public final CommandType type;
    public final int index;          // For MARK, UNMARK, DELETE
    public final String taskInput;   // For TODO, DEADLINE, EVENT, FIND

    /**
     * Constructs a Command object with the specified type, index, and task input.
     *
     * @param type The type of command (BYE, HELP, LIST, MARK, UNMARK, DELETE, TODO, DEADLINE, EVENT, INVALID, FIND)
     * @param index The index of the task in the task list (for commands that operate on a task)
     * @param taskInput The raw user input string (for commands that create a new task)
     */
    public Command(CommandType type, int index, String taskInput) {
        this.type = type;
        this.index = index;
        this.taskInput = taskInput;
    }

    /**
     * Constructs a Command object for command types that do not require an index or task input (e.g., BYE, HELP, LIST).
     * The {@code index} is set to -1 and {@code taskInput} is set to {@code null} as placeholders.
     *
     * @param type Command types that don't require indexing nor input: BYE, HELP, LIST.
     */    public Command(CommandType type) {
        // calls constructor for normal indexed type, but with -1 and null as placeholder
        this(type, -1, null);
    }
}

