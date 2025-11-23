package command;

import task.TaskList;
import ui.Ui;
import storage.Storage;

/**
 * Represents an executable command in the Clam.Clam chatbot program.
 * Each possible command in the program will be defined in a class that
 * extends this abstract class, implementing the {@link #execute(TaskList, Ui, Storage)}
 * method.
 */
public abstract class Command {
    protected final String input;

    /**
     * Constructs a {@link Command} with the relevant user input.
     *
     * @param input the raw user input that triggered the command
     */
    public Command(String input) {
        this.input = input;
    }

    /**
     * Executes the relevant command with the given {@link TaskList}, {@link Ui}
     * and {@link Storage}.
     *
     * @param tasklist the {@link TaskList} where tasks are stored
     * @param ui the {@link Ui} that the user interacts with
     * @param storage the {@link Storage} that retrieves and updates the save file
     * @throws Exception if any errors occur during the execution of the command
     */
    public abstract void execute(TaskList tasklist, Ui ui, Storage storage) throws Exception;

    /**
     * Returns the help message associated with the command
     *
     * @return the corresponding help message
     */
    public abstract String getHelpMessage();

}
