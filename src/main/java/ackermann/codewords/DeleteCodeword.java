package ackermann.codewords;

import ackermann.exceptions.InvalidTargetException;
import ackermann.functions.TaskList;
import ackermann.task.Task;

/**
 * Handles Delete Codeword
 */
public class DeleteCodeword extends Codeword {
    private TaskList tasks;
    private int id;

    /**
     * Constructor for DeleteCodeword
     * @param tasks tasks that contain task to delete
     * @param id id of task to delete
     */
    public DeleteCodeword(TaskList tasks, int id) {
        this.tasks = tasks;
        this.id = id;
    }

    @Override
    public String execute() throws InvalidTargetException {
        try {
            StringBuilder stringBuilder = new StringBuilder();
            Task tempTask = this.tasks.get(this.id);
            tasks.delete(id);

            stringBuilder.append("Ok bro, I've removed this task:")
                    .append("\n")
                    .append(tempTask.toString())
                    .append("\n")
                    .append("Now you have ")
                    .append(this.tasks.size())
                    .append(" tasks in the list.");
            return String.valueOf(stringBuilder);
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            throw new InvalidTargetException();
        }
    }
}
