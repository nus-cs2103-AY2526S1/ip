package ackermann.codewords;

import ackermann.exceptions.InvalidTargetException;
import ackermann.functions.TaskList;

/**
 * Handles Mark Codeword
 */
public class MarkCodeword extends Codeword {
    private TaskList tasks;
    private int id;

    /**
     * Constructor for TagCodeword
     * @param tasks tasks that contain task to mark
     * @param id id of task to mark
     */
    public MarkCodeword(TaskList tasks, int id) {
        this.tasks = tasks;
        this.id = id;
    }

    @Override
    public String execute() throws InvalidTargetException {
        try {
            this.tasks.get(this.id).markAsDone();
            return "Nice one bro! I've marked this task as done:\n" + this.tasks.get(id).toString();
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            throw new InvalidTargetException();
        }
    }
}
