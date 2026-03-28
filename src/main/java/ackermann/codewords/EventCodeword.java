package ackermann.codewords;

import java.time.LocalDate;

import javax.naming.InvalidNameException;

import ackermann.exceptions.CheckedException;
import ackermann.functions.TaskList;
import ackermann.task.Event;
import ackermann.task.Task;

/**
 * Handles Event Codeword
 */
public class EventCodeword extends Codeword {
    private TaskList tasks;
    private String name;
    private LocalDate from;
    private LocalDate to;

    /**
     * Constructor for EventCodeword
     * @param tasks task list to append
     * @param name name of event
     * @param from start date
     * @param to end date
     */
    public EventCodeword(TaskList tasks, String name, LocalDate from, LocalDate to) {
        this.tasks = tasks;
        this.name = name;
        this.from = from;
        this.to = to;
    }

    @Override
    public String execute() throws CheckedException {
        StringBuilder string = new StringBuilder();
        Task event = new Event(name, from, to);
        this.tasks.add(event);
        return String.valueOf(string
                .append("Roger bro. I've added this Event:\n")
                .append(event).append("\nNow you have ")
                .append(this.tasks.size())
                .append(" tasks in the list.").toString());
    }
}
