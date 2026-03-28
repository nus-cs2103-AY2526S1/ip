package ackermann.codewords;

import ackermann.exceptions.InvalidTargetException;
import ackermann.functions.TaskList;

/**
 * Handles Unmark Codeword
 */
public class UnmarkCodeword extends Codeword {
    private TaskList tasks;
    private int id;

    /**
     * Constructor for List
     * @param tasks tasks that contain task to unmark
     * @param id id of task to unmark
     */
    public UnmarkCodeword(TaskList tasks, int id) {
        this.tasks = tasks;
        this.id = id;
    }

    @Override
    public String execute() throws InvalidTargetException {
        try {
            this.tasks.get(this.id).markAsUndone();
            return "Ok bro, I've marked this task as not done yet:\n" + this.tasks.get(id).toString();
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            throw new InvalidTargetException();
        }
    }
}
