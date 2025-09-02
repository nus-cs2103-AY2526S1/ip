package sora;

import java.io.IOException;

import sora.list.TaskList;
import sora.storage.Storage;


/**
 * The {@code Sora} class is the main entry point of the Sora chatbot application.
 */
public class Sora {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a new instance of {@code Sora}.
     *
     * @param filePath the file path to the data storage file.
     */
    public Sora(String filePath) {
        this.ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load().getFullTasks());
        } catch (IOException e) {
            ui.showError("Cannot load storage tasks");
            tasks = new TaskList();
        }
    }

    /**
     * Runs the main loop of the application.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String command = ui.readCommand();
                if (command.equals("bye")) {
                    isExit = true;
                }
                Parser.parse(command, tasks, ui, storage);
            } catch (SoraException e) {
                ui.showError(e.getMessage());
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
}
