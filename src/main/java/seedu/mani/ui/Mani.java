package seedu.mani.ui;

import seedu.mani.storage.Storage;
import seedu.mani.task.TaskList;
import seedu.mani.parser.Parser;
import java.io.IOException;

/**
 * Main class for the Mani task management application.
 * Initializes the application and manages the main execution loop.
 */
public class Mani {
    private TaskList memory;
    private Ui ui;

    /**
     * Constructs a Mani instance and initializes the task list from storage.
     *
     * @param filePath
     */
    public Mani(String filePath) {
        ui = new Ui();
        try {
            memory = new TaskList(Storage.loadTasks(filePath));
        } catch (IOException e) {
            ui.errorMessage(e.getMessage());
            memory = new TaskList();
        }
    }

    /**
     * Runs the main application loop, processing user commands until exit.
     */
    public void run() {
        ui.welcomeMessage();

        boolean endMani = false;
        try {
            endMani = Parser.parse(ui.readCommand(), ui, memory);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        while (!endMani) {
            try {
                endMani = Parser.parse(ui.readCommand(), ui, memory);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        ui.goodbyeMessage();
        ui.closeScanner();
    }

    public String getResponse(String input) {
        StringBuilder response = new StringBuilder();
        try {
            Parser.parse(input, new Ui() {
                @Override
                public void systemMessage(String message) {
                    response.append(message).append("\n");
                }

                @Override
                public void errorMessage(String message) {
                    response.append("Error: ").append(message).append("\n");
                }
            }, memory);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return response.toString().trim();
    }

    /**
     * Main entry point of application.
     *
     * @param args
     */
    public static void main(String[] args) {
        new Mani("data/tasks.txt").run();
    }
}
