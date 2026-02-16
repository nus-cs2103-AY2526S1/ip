package yang.parser;

import yang.task.Task;

/**
 * Immutable result of executing a user command.
 * <p>
 * Use the static factory methods (e.g., {@link #added(Task)}, {@link #list()})
 * to create specific kinds of results.
 * </p>
 */
public class CommandResult {

    /**
     * Kinds of command outcomes that the UI can render.
     */
    public enum Type {
        NONE, ADDED, DELETED, LIST, MARKED, UNMARKED, FOUND
    }

    /** The outcome type. */
    public final Type type;

    /** The task relevant to this result, if any (e.g., added/marked/deleted). */
    public final Task task;

    /** The keyword used for a {@code FIND} result; {@code null} otherwise. */
    public final String keyword;

    private CommandResult(Type type, Task task, String keyword) {
        this.type = type;
        this.task = task;
        this.keyword = keyword;
    }

    private CommandResult(Type type, Task task) {
        this(type, task, null);
    }

    /**
     * Creates a result representing the list command.
     *
     * @return a list command result
     */
    public static CommandResult list() {
        return new CommandResult(Type.LIST, null);
    }

    /**
     * Creates a result representing a newly added task.
     *
     * @param t the task that was added
     * @return an add command result
     */
    public static CommandResult added(Task t) {
        return new CommandResult(Type.ADDED, t);
    }

    /**
     * Creates a result representing a deleted task.
     *
     * @param t the task that was deleted
     * @return a delete command result
     */
    public static CommandResult deleted(Task t) {
        return new CommandResult(Type.DELETED, t);
    }

    /**
     * Creates a result representing a task marked as done.
     *
     * @param t the task that was marked
     * @return a mark command result
     */
    public static CommandResult marked(Task t) {
        return new CommandResult(Type.MARKED, t);
    }

    /**
     * Creates a result representing a task marked as not done.
     *
     * @param t the task that was unmarked
     * @return an unmark command result
     */
    public static CommandResult unmarked(Task t) {
        return new CommandResult(Type.UNMARKED, t);
    }

    public static CommandResult found(String keyword) {
        return new CommandResult(Type.FOUND, null, keyword);
    }
}
