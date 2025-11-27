package whchenyicn;

import whchenyicn.exceptions.whchenyicnExceptions;
import whchenyicn.task.TaskList;
import whchenyicn.command.Command;
import whchenyicn.ui.Parser;
import whchenyicn.ui.Storage;
import whchenyicn.ui.Ui;

/**
 * Main class to run the whchenyicn chatbot.
 */
public class whchenyicn {
    private TaskList tlist = new TaskList(100);
    private Storage storage = new Storage();
    private static Ui ui = new Ui();

    /**
     * Initializes application state.
     * Prints welcome message, load tasks from storage, show loaded tasks.
     */
    private void start() {
        ui.printWelcome();
        TaskList load = storage.load();
        if (!load.isEmpty()) {
            tlist.clear();
            tlist.addAll(load.asList());
            ui.printLoad(tlist);
        }
    }

    /**
     * Runs the loop that reads line of user input.
     * Parses input into command and executes command.
     * Catches errors and prints message.
     */
    public void run() {
        start();
        boolean isExit = false;
        while (!isExit) {
            try {
                String s = ui.readCommand();
                Command c = Parser.parse(s);
                c.execute(tlist, ui, storage);
                isExit = c.isExit();
            }
            catch (whchenyicnExceptions e){
                ui.printError(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new whchenyicn().run();

        }
    }


