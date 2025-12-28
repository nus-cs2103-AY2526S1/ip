package tsunderechan;

import java.io.FileNotFoundException;
import java.io.IOException;

import tsunderechan.command.Command;
import tsunderechan.parser.Parser;
import tsunderechan.storage.Storage;
import tsunderechan.task.TaskList;
import tsunderechan.ui.Ui;

/**
 * Represents a chatbot application.
 */
public class TsundereChan {
    private TaskList tasks;
    private final Storage storage;
    private Ui ui;
    private String commandType;
    private String loadErrorMessage;

    /**
     * Instantiates a TsundereChan object, loading from the specified filePath.
     * If no save file found in filePath, start from clean slate.
     *
     * @param filePath Path to find the save file to load from.
     */
    public TsundereChan(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        commandType = "";
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            tasks = new TaskList();
        } catch (IllegalArgumentException e) {
            tasks = new TaskList();
            loadErrorMessage = e.getMessage();
        }
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input, ui);
            commandType = c.getClass().getSimpleName();
            return c.execute(tasks, ui, storage);
        } catch (IllegalArgumentException | IOException e) {
            commandType = "InvalidCommand";
            return e.getMessage();
        }
    }

    /**
     * Returns a String with the specified command type
     */
    public String getCommandType() {
        return commandType;
    }

    /**
     * Returns a String with contained load error message.
     */
    public String getLoadErrorMessage() {
        return loadErrorMessage;
    }
}
