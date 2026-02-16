package guibot.task;

/**
 * Represents the types of tasks.
 */
public enum TaskType {
    TODO(new String[] {" "}, "todo <description of task>"),
    DEADLINE(new String[] {" ", " /by "}, "deadline <description of task> /by <deadline of task>"),
    EVENT(new String[] {" ", " /from ", " /to "},
            "event <description of event> /from <start of event> /to <end of event>");

    private final String[] splitters;
    private final String expectedInputFormat;

    private TaskType(String [] splitters, String expectedInputFormat) {
        this.splitters = splitters;
        this.expectedInputFormat = expectedInputFormat;
    }

    /**
     * Get the list of strings to split user input by.
     */
    public String[] getSplitters() {
        return splitters;
    }

    /**
     * Get the expected format of the command.
     */
    public String getExpectedInputFormat() {
        return expectedInputFormat;
    }
}
