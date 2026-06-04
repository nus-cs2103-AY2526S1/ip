package sora;

import java.io.IOException;

import sora.list.TaskList;
import sora.storage.Storage;


/**
 * The {@code Sora} class is the main entry point of the Sora chatbot application.
 */
public class Sora {
    private static Storage storage;
    private static TaskList tasks;
    private static Ui ui;

    private boolean isExit = false;

    /**
     * Constructs a new instance of {@code Sora}.
     *
     * @param filePath the file path to the data storage file.
     */
    public Sora(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load().getFullTasks());
        } catch (IOException e) {
            System.out.print(ui.showError("Cannot load storage tasks"));
            tasks = new TaskList();
        }
    }

    /**
     * Runs the main loop of the application.
     */
    public void run() {
        System.out.print(ui.showWelcome());
        while (!isExit) {
            try {
                String command = ui.readCommand();
                String output = executeCommand(command);
                System.out.print(output);
            } catch (SoraException e) {
                System.out.print(ui.showError(e.getMessage()));
            }
        }
    }

    /**
     * Executes a user command by parsing it and performing the corresponding action.
     * <p>
     * If the command is "bye", the application will be flagged to exit. Otherwise,
     * the command is delegated to the {@link Parser#parse(String, TaskList, Ui, Storage)}
     * method to perform the required task operations.
     * </p>
     *
     * @param command the command input from the user
     * @throws SoraException if the command is invalid or an error occurs during execution
     */
    private String executeCommand(String command) throws SoraException {
        if (command.equals("bye")) {
            isExit = true;
        }
        return Parser.parse(command, tasks, ui, storage);
    }

    /**
     * The main entry point of the application.
     *
     * @param args command-line arguments.
     */
    public static void main(String[] args) {
        new Sora("./data/sora.txt").run();
    }

    /**
     * Generates a response for the user's chat message.
     */
    public static String getResponse(String input) throws SoraException {
        return Parser.parse(input, tasks, ui, storage);
    }
}
