package app;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import command.Command;
import exception.GenieweenieException;
import parser.Parser;
import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;


/**
 * Main application class for GenieWeenie task manager.
 */
public class GenieWeenie {


    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;

    private String commandType;

    // Add a no-argument constructor for JavaFX compatibility
    public GenieWeenie() throws IOException {
        this("data/genie.txt"); // Default file path
    }

    /**
     * Creates a new GenieWeenie application.
     *
     * @param filePath file path for storage
     * @throws IOException if file cannot be loaded
     */
    public GenieWeenie(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        Task[] loadedTasks = new Task[0];
        try {
            loadedTasks = storage.load().toArray(new Task[0]);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (GenieweenieException e) {
            ui.showError("Error loading tasks: " + e.getMessage());
        }
        tasks = new TaskList(new ArrayList<>(Arrays.asList(loadedTasks)));
    }


    /**
     * Runs the application.
     */
    public void run() {
        ui.showWelcome();
        Scanner in = new Scanner(System.in);
        boolean isExit = false;
        while (!isExit) {
            String fullCommand = in.nextLine();
            try {
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (GenieweenieException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    // Gets input from User
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            String result = c.execute(tasks, ui, storage);
            commandType = c.getClass().getSimpleName();
            return result;
        } catch (GenieweenieException e) {
            // Provide helpful format suggestions
            String helpMessage = "";

            if (input.startsWith("todo")) {
                helpMessage = "\nFormat: todo <description>";
            } else if (input.startsWith("deadline")) {
                helpMessage = "\nFormat: deadline <description> /by <yyyy-mm-dd>";
            } else if (input.startsWith("event")) {
                helpMessage = "\nFormat: event <description> /at <yyyy-mm-dd HH:mm>";
            } else if (input.startsWith("find")) {
                helpMessage = "\nFormat: find <keyword>";
            }

            return "Error: " + e.getMessage() + helpMessage;
        }
    }

    public String getCommandType() {
        return commandType;
    }
}
