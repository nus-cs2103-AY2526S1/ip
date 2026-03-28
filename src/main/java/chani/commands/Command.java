package chani.commands;

import chani.Storage;
import chani.TaskList;
import chani.Ui;

/**
 * Represents an abstract command in the Chani application.
 * <p>
 * A Command is an action that can be executed on a {@link TaskList} with
 * the help of a {@link Ui} for user interaction and {@link Storage} for persistence.
 * Subclasses must implement the {@link #execute(TaskList, Ui, Storage)} method.
 * </p>
 */
public abstract class Command {

    /** The raw command string provided by the user. */
    protected String command;
    /** Any additional arguments associated with the command. */
    protected String[] args;

    /**
     * Constructs a new Command.
     * @param command the main command keyword
     * @param args optional arguments for the command
     */
    public Command(String command, String... args) {
        this.command = command;
        this.args = args;
    }

    /**
     * Executes this command using the provided task list, UI, and storage.
     * @param taskList the task list on which the command operates
     * @param ui the user interface for interaction
     * @param storage the storage for saving/loading tasks
     */
    public abstract String execute(TaskList taskList, Ui ui, Storage storage);
}
