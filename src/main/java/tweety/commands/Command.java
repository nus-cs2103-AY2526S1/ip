package tweety.commands;

import tweety.exceptions.TweetyException;

import tweety.storage.Storage;

import tweety.tasks.TaskList;

import tweety.ui.Ui;

/**
 * Abstract base class for all commands in the Tweety application.
 * Defines the common structure and behavior that all command implementations must follow.
 * Uses the Command pattern to encapsulate command execution logic.
 */
public abstract class Command {

    /**
     * Enumeration of all supported command types in the Tweety application.
     * Each type represents a specific action that can be performed on tasks.
     */
    public enum CommandType {
        MARK, UNMARK, DELETE, LIST, BYE, EVENT, TODO, DEADLINE, FIND, EDIT
    }

    private CommandType type;

    /**
     * Constructs a new Command with the specified type.
     * Initialises the command with its corresponding command type.
     *
     * @param type The type of command being created.
     */
    public Command(CommandType type) {
        this.type = type;
    }

    /**
     * Executes this command with the provided application components.
     * Implementation varies based on the specific command type and its required operations.
     *
     * @param tasks The task list to operate on.
     * @param ui The user interface for displaying messages.
     * @param storage The storage component for persisting data.
     * @throws TweetyException If command execution fails or encounters invalid conditions.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws TweetyException;

    /**
     * Determines whether this command should cause the application to exit.
     * Most commands return false, but exit commands (like BYE) return true.
     *
     * @return True if the application should exit after executing this command, false otherwise.
     */
    public abstract boolean isExit();
}