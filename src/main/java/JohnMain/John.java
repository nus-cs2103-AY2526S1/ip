package JohnMain;

import Command.Command;
import JohnException.JohnException;
import Parser.Parser;
import Task.TaskList;
import UI.Ui;

/**
 * Entry point of the John.John application. Coordinates the main program loop:
 * loading tasks from storage, reading user commands, executing them,
 * and saving results back to disk upon exit.
 */
public class John {
    private static Storage storage;
    private static TaskList tasks;
    private final Ui ui;

    /**
     * Creates a John.John instance with the default storage path and
     * supporting components.
     */
    public John() {
        ui = new Ui();
        storage = new Storage();
        tasks = storage.load();
        System.out.println("[John.John] tasks @" + System.identityHashCode(tasks) + " after load size=" + tasks.getSize());
    }

    /**
     * Starts the program loop. Continues reading commands from
     * the UI, parsing them, executing the corresponding Command, and
     * displaying results until an exit command is issued.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;

        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui);
                storage.save(tasks);
                isExit = c.isExit();
            } catch (JohnException e) {
                ui.showError(e.getMessage());
            } finally {
                Ui.showLine();
            }
        }
    }

    private boolean exitRequested = false;

    /**
     * Returns the exit request status to determine whether the app should exit.
     */
    public boolean isExitRequested() {
        return exitRequested;
    }

    /**
     * Get the UI output for each input command as a String.
     *
     * @param input Input from user to manipulate the task list.
     * @return String representing the response from the UI.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            String response = c.executeAndReturn(tasks);
            storage.save(tasks);
            this.exitRequested = c.isExit();
            return response;
        } catch (JohnException e) {
            this.exitRequested = false;
            return e.getMessage();
        }
    }

    /**
     * Launches John.John from the command line.
     *
     * @param args Optional CLI arguments (ignored by default).
     */
    public static void main(String[] args) {
        new John().run();
    }
}