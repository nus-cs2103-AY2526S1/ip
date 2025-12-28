package benn.commands;

import benn.tasks.TaskManager;

/**
 * Represents an abstract command in Benn the Chatbot.
 *
 * <p>All concrete command classes (e.g., {@code AddTodoCommand},
 * {@code AddDeadlineCommand}, {@code ListCommand}, etc.)
 * should extend this class and implement the {@link #execute(TaskManager)}
 * method to define their behavior.</p>
 *
 * <p>Each command stores the raw user input that triggered it
 * and a flag indicating whether the chatbot should exit after
 * the command is executed.</p>
 */
public abstract class Command {
    protected boolean shouldExit;
    protected String input;

    /**
     * Executes the logic of the command.
     *
     * @param taskManager the {@link TaskManager} that manages the list of tasks
     * @return a user-facing string message produced by the command
     */
    public abstract String execute(TaskManager taskManager);

    /**
     * Returns whether this command signals the chatbot to exit.
     *
     * @return {@code true} if the chatbot should exit, {@code false} otherwise
     */
    public boolean shouldExit() {
        return shouldExit;
    }
}
