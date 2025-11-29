package cate.command;

import java.util.Stack;

/**
 * Manages the history of executed commands that support undo functionality.
 * Allows tracking, retrieving, and undoing previous commands.
 */
public class CommandManager {
    private final Stack<Command> history = new Stack<>();

    /**
     * Adds a command to the history if it supports undo.
     *
     * @param command the command to add; only commands with {@link Command#canUndo()} true are stored
     */
    public void addCommand(Command command) {
        if (command.canUndo()) {
            history.push(command);
        }
    }

    /**
     * Removes and returns the most recently executed undoable command.
     *
     * @return the last undoable command, or null if no commands are available
     */
    public Command popLastCommand() {
        if (!history.isEmpty()) {
            return history.pop();
        }
        return null;
    }

    /**
     * Checks whether there are any undoable commands in the history.
     *
     * @return true if there is at least one undoable command; false otherwise
     */
    public boolean hasUndoableCommand() {
        return !history.isEmpty();
    }
}
