package bob.ui;

/**
 * User Interface for the application.
 */
public class Ui {
    private static final String LINE_SEPARATOR = "=".repeat(80);
    private static final String ERROR_PREFIX = "Error: ";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_RESET = "\u001B[0m";
    
    private final StringBuilder responseBuffer = new StringBuilder();

    /**
     * Prints a line separator.
     */
    public void printLine() {
        System.out.println(LINE_SEPARATOR);
    }

    /**
     * Shows an error message.
     *
     * @param message the error message to display.
     */
    public void showError(String message) {
        responseBuffer.append(ERROR_PREFIX).append(message).append("\n");
        System.out.println(ANSI_RED + ERROR_PREFIX + message + ANSI_RESET);
    }

    /**
     * Prints a section header.
     * <p>
     * It is just the string s, with a line below.
     *
     * @param s the section content to print.
     */
    public void printSection(String s) {
        responseBuffer.append(s);
        System.out.println(s);
        this.printLine();
    }

    /**
     * Prints the available commands.
     */
    public void printHelp() {
        String helpMessage = """
                Available commands:
                    list - List all tasks
                    mark <task number> - Mark a task as done. Separate tasks by a space
                    unmark <task number> - Unmark a task as not done
                    todo <description> - Add a task without any date/time attached to it
                    deadline <description> /by <datetime> - Add a task that need to be done before a specific date/time
                    event <description> /from <start datetime> /to <end datetime> - Add task that start at a specific
                                        date/time and ends at a specific date/time
                    delete <task number> - Delete a task from the list
                    find <search keyword> - Search for a task that contains <search keyword> in the description.
                    bye - Exit the program
                Note:
                    All datetime should be in the format of "dd/MM/yyyy HHmm", for example: 27/08/2025 1000
                """;
        responseBuffer.append(helpMessage);
        System.out.println(helpMessage);
    }

    /**
     * Gets the accumulated response and clears the buffer.
     *
     * @return the accumulated response from operations
     */
    public String getResponse() {
        String res = responseBuffer.toString();
        responseBuffer.setLength(0); // Clear the buffer
        return res;
    }
}
