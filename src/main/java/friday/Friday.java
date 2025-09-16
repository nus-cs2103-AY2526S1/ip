package friday;

/**
 * The main Friday application class that serves as the entry point for the task management system.
 * This class acts as a facade for the UI layer and handles the core application logic.
 * 
 * Friday is a task management chatbot that allows users to:
 * - Create different types of tasks (ToDo, Deadline, Event)
 * - Manage task status (mark/unmark as done)
 * - Search and filter tasks
 * - Persist tasks to storage
 * 
 * @author Friday Development Team
 * @version 2.0
 * @since 1.0
 */
public class Friday {
    /** The user interface handler that manages all user interactions */
    private final Ui ui;

    /**
     * Constructs a new Friday application instance.
     * Initializes the UI component which handles task management and user interactions.
     */
    public Friday() {
        this.ui = new Ui();
    }

    /**
     * Generates a response for the user's chat message.
     * This is the main entry point for processing user commands.
     * 
     * @param input The user's input command as a string
     * @return A string response containing the result of the command execution
     * @throws IllegalArgumentException if the input is null or empty
     */
    public String getResponse(String input) {
        if (input == null || input.trim().isEmpty()) {
            return "Please enter a valid command. Type 'help' for assistance.";
        }
        return ui.getResponse(input);
    }
}
