package july.command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import july.data.Storage;
import july.error.JulyException;
import july.tasks.Task;
import july.ui.Ui;

/**
 * Abstract base class for all commands in the July application.
 * Provides the interface for executing commands and checking for termination.
 */
public abstract class Command {
    protected List<String> responses;

    public Command() {
        this.responses = new ArrayList<>();
    }

    /**
     * Executes the command with the given tasks, UI, and storage.
     *
     * @param tasks the ArrayList of tasks
     * @param ui the user interface component
     * @param storage the storage component
     * @throws JulyException if there is an error executing the command
     */
    public abstract void execute(ArrayList<Task> tasks, Ui ui, Storage storage) throws JulyException;

    /**
     * Checks if the command should terminate the program.
     *
     * @return true if the program should terminate, false otherwise
     */
    public boolean isDone() {
        return false;
    }

    /**
     * Adds one or more response messages to the command's response list.
     *
     * @param messages The messages to add to the response
     */
    protected void addResponses(String... messages) {
        responses.addAll(Arrays.asList(messages));
    }

    /**
     * Gets all responses accumulated during command execution.
     *
     * @return List of response messages
     */
    public List<String> getResponses() {
        return responses;
    }

    /**
     * Gets a single concatenated response string, joining multiple responses with newlines.
     *
     * @return Combined response string
     */
    public String getResponse() {
        return String.join("\n", responses);
    }
}
