package whchenyicn;

import whchenyicn.exceptions.WhchenyicnException;
import whchenyicn.task.TaskList;
import whchenyicn.command.Command;
import whchenyicn.ui.Parser;
import whchenyicn.ui.Storage;
import whchenyicn.ui.Ui;

public class Whchenyicn {

    /**
     * Main class to run the whchenyicn chatbot.
     */
    private TaskList tlist = new TaskList(100);
    private Storage storage = new Storage();
    private static Ui ui = new Ui();
    private boolean isExit = false;

    /**
     * Initializes application state.
     * Prints welcome message, load tasks from storage, show loaded tasks.
     */
    public String start() {
        String str = "";
        str += ui.printWelcome() + "\n";
        TaskList load = storage.load();
        if (!load.isEmpty()) {
            tlist.clear();
            tlist.addAll(load.asList());
            str += ui.printLoad(tlist);
        }

        return str;
    }

    public String getResponse(String userInput) {
        String str;
        start();
        try {
            Command c = Parser.parse(userInput);
            str = c.execute(tlist, ui, storage);
            if (c.isExit()) {
                this.isExit = true;
            }
        } catch (WhchenyicnException e) {
            return ui.printError(e.getMessage());
        }
        return str;
    }

    public boolean isExit() {
        return this.isExit;
    }
}


