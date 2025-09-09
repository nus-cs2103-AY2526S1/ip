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
        boolean isExit = false;
        while (!isExit) {
            try {
                String command = ui.readCommand();
                if (command.equals("bye")) {
                    isExit = true;
                }
                String output = Parser.parse(command, tasks, ui, storage);
                System.out.print(output);
            } catch (SoraException e) {
                System.out.print(ui.showError(e.getMessage()));
            }
        }
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
    public static String getResponse(String input) {
        try {
            return Parser.parse(input, tasks, ui, storage);
        } catch (SoraException e) {
            return e.getMessage();
        }
    }
}
