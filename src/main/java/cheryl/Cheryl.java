package cheryl;

import cheryl.command.Command;
import cheryl.util.DukeException;
import cheryl.util.Parser;
import cheryl.util.Storage;
import cheryl.util.TaskList;
import cheryl.util.Ui;

public class Cheryl {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Cheryl(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            ui.showError("Warning: could not load tasks (file may be corrupted). Starting with an empty list.");
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Cheryl("./data/task.txt").run();
    }

    /**
     * Returns the welcome message for the GUI by invoking Ui.showWelcome()
     * and returning the accumulated buffer.
     */
    public String getWelcome() {
        // ensure we start with a clean buffer
        ui.clearLastOutput();
        ui.showWelcome();
        return ui.getLastOutput();
    }

    public String getResponse(String input) {
        ui.clearLastOutput();

        try {
            Command c = Parser.parse(input);
            c.execute(tasks, ui, storage);
            String resp = ui.getLastOutput();
            return resp == null ? "" : resp;
        } catch (DukeException e) {
            // Ensure GUI also receives the error text
            String err = "☹ OOPS!!! " + e.getMessage();
            ui.showError(err);
            return ui.getLastOutput();
        }
    }
}