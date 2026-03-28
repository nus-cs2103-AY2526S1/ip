package ackermann.codewords;

import java.time.LocalDate;

import javax.naming.InvalidNameException;

import ackermann.exceptions.task.EmptyNameException;
import ackermann.functions.TaskList;
import ackermann.task.Deadline;
import ackermann.task.Task;

/**
 * Handles Deadline Codeword
 */
public class DeadlineCodeword extends Codeword {
    private TaskList tasks;
    private String name;
    private LocalDate by;

    /**
     * Constructor for DeadlineCodeword
     * @param tasks task list to append
     * @param name name of task
     * @param by deadline
     */
    public DeadlineCodeword(TaskList tasks, String name, LocalDate by) {
        this.tasks = tasks;
        this.name = name;
        this.by = by;
    }

    @Override
    public String execute() throws EmptyNameException {
        StringBuilder string = new StringBuilder();
        Task deadline = new Deadline(this.name, this.by);
        this.tasks.add(deadline);
        return String.valueOf(string
                .append("Can bro. I've added this Deadline:\n")
                .append(deadline)
                .append("\nNow you have ")
                .append(this.tasks.size())
                .append(" tasks in the list.").toString());
    }
}
