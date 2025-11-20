package jobe;

import jobe.command.Command;
import jobe.exception.JobeException;
import jobe.parser.Parser;
import jobe.storage.Storage;
import jobe.task.TaskList;
import jobe.ui.Ui;

/**
 * Main class for the Jobe Application.
 */
public class Jobe {
    private final TaskList taskList;
    private final Ui ui;
    private final Storage storage;
    private String commandType;
    
    /**
     * Initialises a Jobe object.
     */
    public Jobe() {
        this.taskList = new TaskList();
        this.ui = new Ui();
        this.storage = new Storage(taskList);
    }
    
    /**
     * Gets response to user input for Jobe to display.
     *
     * @param input User's input.
     * @return Jobe's response.
     */
    public String getResponse(String input) {
        assert taskList != null : "TaskList should never be null";
        assert ui != null : "Ui should never be null";
        assert storage != null : "Storage should never be null";
        
        try {
            if (input.isBlank()) {
                throw new JobeException("OOPS!!!! You forgot to type something!");
            }
            
            Command c = Parser.parse(input);
            this.commandType = c.getClass().getSimpleName();
            c.execute(this.taskList, this.ui, this.storage);
            return this.ui.getResponse();
        } catch (JobeException e) {
            return e.getMessage();
        } catch (Exception e) {
            return e.getMessage() + " Try again later!";
        }
    }
    
    public Storage getStorage() {
        return this.storage;
    }
    
    public String getCommandType() {
        return this.commandType;
    }
    
    /**
     * Entry point of the Jobe application.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        Jobe jobe = new Jobe();
    }
}
