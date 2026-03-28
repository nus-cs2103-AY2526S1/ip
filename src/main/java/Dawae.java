import java.io.IOException;
import java.util.ArrayList;

import commands.Command;
import exceptions.DawaeException;
import parser.Parser;
import storage.Storage;
import taskstuff.Task;
import taskstuff.TaskList;
import ui.Ui;

public class Dawae {
    private final Storage storage;
    private TaskList taskList;
    private final Ui ui;
    
    /**
     * Creates the application instance and initializes UI, storage, and task list.
     * Loads tasks from the specified file if available; otherwise starts with an empty list.
     *
     * @param filePath path to the save file (e.g., {@code Dawaetasks.txt}).
     */
    public Dawae(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            ArrayList<Task> storageArr= this.storage.load();
            assert storageArr != null : "Loaded tasks from storage should not be null";
            this.taskList = new TaskList(storageArr);
            assert this.taskList != null : "TaskList should be initialized";
        } catch (IOException e) {
            this.ui.showLoadingError();
        } catch (DawaeException e) {
            this.ui.showError(e.getMessage());
        }
    }
 
    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        try {
            assert input != null;
            this.ui.showLine();
            Command c = Parser.parse(input);
            assert c != null : "Parsed command should not be null";
            return c.execute(this.taskList, this.ui, this.storage);
        } catch (DawaeException e) {
            this.ui.showError(e.getMessage());
            return e.getMessage();
        } finally {
            this.ui.showLine();
        }
    }
}