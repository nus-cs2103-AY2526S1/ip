package khat;

import java.io.FileNotFoundException;

import khat.command.Command;
import khat.exception.KhatException;
import khat.storage.Storage;
import khat.task.TaskList;
import khat.ui.Ui;

/**
 * This is the main class for the Khat chatbot.
 */
public class Khat {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a Khat instance with specified file path to store tasks to the hard disk.
     * Initializes the storage, user interface, and loads tasks from storage.
     * If loading tasks fails, initializes an empty task list.
     *
     * @param filePath Path to the file for tasks.
     */
    public Khat(String filePath) {
        assert filePath != null : "File path should not be null";
        assert !filePath.isEmpty() : "File path should not be empty";
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the main application loop, where it reads and executes user commands
     * until the exit command is received.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String command = ui.readCommand();
                ui.showDivider();
                Command c = Parser.parse(command);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (KhatException e) {
                ui.showMessage(e.getMessage());
            } finally {
                ui.showDivider();
            }
        }
    }

    public static void main(String[] args) {
        new Khat("./data/KhatTasks.txt").run();
    }

    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            c.execute(tasks, ui, storage);
            return ui.consumeLastMessages();
        } catch (KhatException e) {
            ui.showMessage(e.getMessage());
            return ui.consumeLastMessages();
        }
    }
}
