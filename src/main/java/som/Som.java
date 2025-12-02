package som;

import som.command.Command;

/**
 * The main chatbot class that handles user interaction, task management, and persistence.
 * <p>This class reads user input, parses commands, manages a list of tasks.</p>
 *
 * @author Darien Tan
 */
public class Som {
    private Ui ui;
    private TaskList tasks;

    /**
     * Creates a new Som Chatbot instance.
     */
    public Som() {
        this.ui = new Ui();
        // Load tasks on startup
        try {
            tasks = new TaskList(Storage.load());
            System.out.println("Loaded " + tasks.size() + " tasks.");
        } catch (SomException e) {
            ui.showLoadingError(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * The main logic of the chatbot.
     * <p>Parses the user's input before to create a command before
     * executing the command</p>
     * @param fullCommand the user's command
     * @return a string showing the status of the command
     */
    public String getResponse(String fullCommand) {
        assert fullCommand != null : "fullCommand should not be null";
        try {
            Command c = Parser.parse(fullCommand);
            return c.execute(tasks, ui);
        } catch (SomException e) {
            return ui.showError(e.getMessage());
        } catch (Exception e) {
            return ui.showError("Something went wrong: " + e.getMessage());
        }
    }
}
