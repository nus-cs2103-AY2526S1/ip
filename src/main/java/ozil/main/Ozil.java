
package ozil.main;

import ozil.command.Command;
import ozil.exception.ErrorMessages;
import ozil.exception.OzilException;

/**
 * Main chatbot class that handles user interactions and task management.
 * Responsible for processing user input and generating appropriate responses.
 */
public class Ozil {
    private static final String FILE_PATH = "data/tasks.txt";
    private static final String EXIT_RESPONSE = "bye";

    private TaskList tasks;
    private Storage storage;

    /**
     * Initializes a new Ozil chatbot instance.
     * Attempts to load tasks from persistent storage during initialization.
     */
    public Ozil() {
        this.storage = new Storage(FILE_PATH);
        try {
            this.tasks = storage.loadStoredTasks();
        } catch (OzilException e) {
            System.err.println("Failed to load tasks: " + e.getMessage());
            this.tasks = new TaskList(); // Initialize empty task list on failure
        }
    }

    /**
     * Returns the current task list.
     *
     * @return the current TaskList instance
     */
    public TaskList getTaskList() {
        return this.tasks;
    }

    /**
     * Processes user input and generates an appropriate response.
     *
     * @param input the user's input text
     * @return the chatbot's response or error message
     */
    public String getResponse(String input) {
        try {
            validateInput(input);

            Command command = Parser.handleInput(input);

            if (command.isTerminatingCommand()) {
                saveTasks();
                return EXIT_RESPONSE;
            } else {
                return command.run(this.tasks);
            }
        } catch (OzilException e) {
            return e.getMessage();
        } catch (Exception e) { //suggested by DeepSeek
            return e.getMessage();
        }
    }

    /**
     * Validates that the user input is not empty or blank.
     *
     * @param input the user input to validate
     * @throws OzilException if input is blank
     */
    private void validateInput(String input) throws OzilException {
        if (input.isBlank()) {
            throw new OzilException(ErrorMessages.nonsenseError());
        }
    }

    /**
     * Saves the current task list to persistent storage.
     *
     * @throws RuntimeException if saving fails
     */
    private void saveTasks() {
        try {
            this.storage.save(this.getTaskList());
        } catch (OzilException e) {
            throw new RuntimeException("Failed to save tasks: " + e.getMessage(), e);
        }
    }
}