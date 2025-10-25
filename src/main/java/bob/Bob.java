package bob;

import bob.command.Command;
import bob.exception.InvalidEventUsageException;
import bob.parser.Parser;
import bob.storage.Storage;
import bob.task.TaskManager;
import bob.ui.Ui;

/**
 * Task managing application
 */
public class Bob {

    /**
     * Entry point for the bot
     * 
     * @param input
     * @return Processed response after executing commands
     */
    public String start(String input) {
        Ui ui = new Ui();
        Storage storage = new Storage();
        TaskManager manager = new TaskManager(storage);
        Parser parser = new Parser(manager);
        boolean isBye = false;
        try {
            Command c = parser.run(input);
            if (c == null) {
                throw new InvalidEventUsageException("");
            }
            String s = c.execute(manager, ui, storage);
            isBye = c.isBye();
            return s;
        } catch (InvalidEventUsageException e) {
            return "";
        }

    }
}
